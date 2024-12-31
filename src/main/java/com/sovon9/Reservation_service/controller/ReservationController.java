package com.sovon9.Reservation_service.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.KafkaException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sovon9.Reservation_service.RoomProxy;
import com.sovon9.Reservation_service.constants.StringConstants;
import com.sovon9.Reservation_service.dto.GuestCommInfo;
import com.sovon9.Reservation_service.dto.ReservationSearchDTO;
import com.sovon9.Reservation_service.dto.RoomDto;
import com.sovon9.Reservation_service.exception.PMSException;
import com.sovon9.Reservation_service.model.ReservationVO;
import com.sovon9.Reservation_service.service.KafkaProducerService;
import com.sovon9.Reservation_service.service.ReservationService;

@RestController
@RequestMapping("/res-service")
public class ReservationController {

	Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	
	@Autowired
	private ReservationService service;
	@Autowired
	private RoomProxy roomProxy;
	@Autowired
	private KafkaProducerService producerService;
	
	/**
	 * 
	 * @param resVO
	 * @return
	 * @throws CloneNotSupportedException 
	 */
	@PostMapping("/reservaion")
	public ResponseEntity<ReservationVO> saveReservation(@RequestBody ReservationVO resVO) throws CloneNotSupportedException
	{
		ReservationVO saveReservationData = null;
		if(resVO.getStatus().equals(StringConstants.MOD))
		{
			Optional<ReservationVO> resData = service.getResData(resVO.getResID());
			if(resData.isPresent())
			{
				ReservationVO reservationVO = resData.get().clone();
				resVO = service.updateModifiedFields(resData.get(), resVO);
				saveReservationData = service.saveReservationData(resVO, reservationVO);
			}
		}
		else
		{
			saveReservationData = service.saveReservationData(resVO);
		}
		if(null!=saveReservationData && null!=saveReservationData.getRoomnum())
		{
			ResponseEntity<RoomDto> roomEntity = roomProxy.upateResRoomStatus(saveReservationData.getResID(),saveReservationData.getRoomnum(), "VB"); // vacant blocked
		    if(roomEntity.getStatusCode() != HttpStatus.OK)
		    {
		    	throw new PMSException("Unable to Save Room Details", roomEntity.getStatusCode().toString());
		    }
		}
		// get guest comm info 
		if(null != saveReservationData.getGuestID())
		{
			GuestCommInfo guestCommInfo = service.getGuestCommInfo(saveReservationData.getGuestID());
			// send email to guest
			if (null != guestCommInfo && null!=guestCommInfo.getEmail())
			{
				guestCommInfo.setResID(saveReservationData.getResID());
				guestCommInfo.setGuestID(saveReservationData.getGuestID());
				if(StringConstants.RES.equals(saveReservationData.getStatus()))
				{
					guestCommInfo.setAction(StringConstants.RESCREATE);
				}
				else if(StringConstants.MOD.equals(saveReservationData.getStatus()))
				{
					guestCommInfo.setAction(StringConstants.RESMODIFY);
				}
				try
				{
					producerService.publish(guestCommInfo);
				}
				catch (KafkaException e)
				{
					LOGGER.error("Kafka exception: {}", e.getMessage(), e);
				}
			}
		}
		return new ResponseEntity<ReservationVO>(saveReservationData, HttpStatus.CREATED);
	}
	
	/**
	 * 
	 * @param resVO
	 * @return
	 */
	@PutMapping("/reservaion/checkin/resID/{resID}")
	public ResponseEntity<ReservationVO> checkInReservation(@PathVariable("resID") Long resID)
	{
		try
		{
			Optional<ReservationVO> resData = service.getResData(resID);
			if(resData.isPresent())
			{
				ReservationVO reservationVO = resData.get();
				reservationVO.setStatus(StringConstants.REG);
				ReservationVO saveReservationData = service.saveReservationData(reservationVO);
				if (null != saveReservationData.getRoomnum())
				{
					ResponseEntity<RoomDto> roomEntity = roomProxy.upateResRoomStatus(saveReservationData.getResID(),
							saveReservationData.getRoomnum(), "OC");
					if (!roomEntity.getStatusCode().is2xxSuccessful())
					{
						throw new PMSException(StringConstants.CHECKIN_ERROR, HttpStatus.BAD_REQUEST.toString());
					}
					// get guest comm info 
					if(null != saveReservationData.getGuestID())
					{
						GuestCommInfo guestCommInfo = service.getGuestCommInfo(saveReservationData.getGuestID());
						// send email to guest
						if (null != guestCommInfo && null!=guestCommInfo.getEmail())
						{
							guestCommInfo.setAction(StringConstants.CHECKIN);
							guestCommInfo.setResID(saveReservationData.getResID());
							guestCommInfo.setRoomNum(saveReservationData.getRoomnum());
							producerService.publish(guestCommInfo);
						}
					}
					return ResponseEntity.ok(saveReservationData);
				}
				else
				{
					throw new PMSException(StringConstants.CHECKIN_ERROR+"Room Number Not Found", HttpStatus.BAD_REQUEST.toString());
				}
			}
			else
			{
				throw new PMSException(StringConstants.CHECKIN_ERROR+"Res Data Not Found", HttpStatus.BAD_REQUEST.toString());
			}
		}
		catch (Exception e) {
			throw new PMSException(StringConstants.CHECKIN_ERROR,  HttpStatus.INTERNAL_SERVER_ERROR.toString());
		}
	}
	
	/**
	 * 
	 * @param resID
	 * @return null if no res found with resID
	 */
	@PutMapping("/reservaion/checkout/resID/{resID}")
	public ResponseEntity<ReservationVO> checkOutReservation(@PathVariable("resID") Long resID)
	{
		Optional<ReservationVO> resData = service.getResData(resID);
		if (resData.isPresent()) {
			ReservationVO resVO = resData.get();
			resVO.setStatus("CO");
			roomProxy.upateRoomStatus(resVO.getRoomnum(), StringConstants.VC);
			resVO = service.saveReservationData(resVO);
			if(null != resVO.getGuestID())
			{
				GuestCommInfo guestCommInfo = service.getGuestCommInfo(resVO.getGuestID());
				// send email to guest
				if (null != guestCommInfo && null!=guestCommInfo.getEmail())
				{
					guestCommInfo.setAction(StringConstants.CHECKOUT);
					guestCommInfo.setResID(resVO.getResID());
					guestCommInfo.setRoomNum(resVO.getRoomnum());
					producerService.publish(guestCommInfo);
				}
			}
			return new ResponseEntity<ReservationVO>(resVO, HttpStatus.OK);
		}
		else
		{
			throw new PMSException("Unable to Find Reservation with RESID: "+resID, HttpStatus.NOT_FOUND.toString());
		}
	}
	
	/**
	 * 
	 * @param resID
	 * @param firstName
	 * @param lastName
	 * @param status
	 * @param arriveDate
	 * @param arriveTime
	 * @param deptDate
	 * @param deptTime
	 * @param roomnum
	 * @param guestID
	 * @return
	 */
	@GetMapping("/search-reservaion")
	public List<ReservationVO> findReservations(
			@RequestParam(value = "resID", required = false) Long resID,
		    @RequestParam(value = "firstName", required = false) String firstName,
		    @RequestParam(value = "lastName", required = false) String lastName,
		    @RequestParam(value = "status", required = false) String status,
		    @RequestParam(value = "arriveDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arriveDate,
		    @RequestParam(value = "arriveTime", required = false) LocalTime arriveTime,
		    @RequestParam(value = "deptDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate deptDate,
		    @RequestParam(value = "deptTime", required = false) LocalTime deptTime,
		    @RequestParam(value = "roomnum", required = false) Integer roomnum,
		    @RequestParam(value = "guestID", required = false) Long guestID)
	{
//		Map<String,Object> queryParam = new HashMap<>();
//		queryParam.put("firstName", firstName);
//		queryParam.put("status", status);
//		queryParam.put("arriveDate", arriveDate);
//		queryParam.put("arriveTime", arriveTime);
//		queryParam.put("deptDate", deptDate);
//		queryParam.put("deptTime", deptTime);
//		queryParam.put("roomnum", roomnum);
//		queryParam.put("guestID", guestID);
		ReservationSearchDTO searchDTO = new ReservationSearchDTO(null, resID, guestID, firstName, lastName, null, status, arriveDate, arriveTime, deptDate, deptTime, status, roomnum);
		List<ReservationVO> findReservationData = service.findReservationData(searchDTO);
		LOGGER.error("=> "+findReservationData);
		return findReservationData;
	}
	
	/**
	 * 
	 * @param status
	 * @return
	 */
	@GetMapping("/reservaion/status/{status}")
	public List<ReservationVO> getAllReservationByStatus(@PathVariable("status") String status)
	{
		LOGGER.error("===================Data Fetched From DB=======================");
		return service.fetchReservationDataByStatus(status);
	}
	
	/**
	 * 
	 * @param resID
	 * @return
	 */
	@GetMapping("/reservaion/resID/{resID}")
	public ResponseEntity<ReservationVO> getReservationDataByResID(@PathVariable("resID") Long resID)
	{
		Optional<ReservationVO> resData = service.getResData(resID);
		if(resData.isEmpty())
		{
			//return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			throw new PMSException("Unable to Find Reservation with RESID: "+resID, HttpStatus.NOT_FOUND.toString());
		}
		return ResponseEntity.ok(resData.get());
	}
	
}
