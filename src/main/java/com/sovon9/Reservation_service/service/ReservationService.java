package com.sovon9.Reservation_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sovon9.Reservation_service.GuestInfo_ServiceProxy;
import com.sovon9.Reservation_service.dto.GuestCommInfo;
import com.sovon9.Reservation_service.dto.ReservationSearchDTO;
import com.sovon9.Reservation_service.log.service.LogService;
import com.sovon9.Reservation_service.model.ReservationVO;
import com.sovon9.Reservation_service.repository.ReservationRepository;

/**
 * 
 * @author Sovon Singha
 *
 */
@Service
public class ReservationService {

	@Autowired
	private ReservationRepository repository;
	@Autowired
	private LogService logService;
	@Autowired
	private GuestInfo_ServiceProxy guestInfo_ServiceProxy;
	
	public ReservationVO saveReservationData(ReservationVO resVO)
	{
		Optional<ReservationVO> oldResData = Optional.empty();
		if(null!=resVO.getResID())
		{
			oldResData = getResData(resVO.getResID());
		}
		ReservationVO save = repository.save(resVO);
		try {
			if(null!=save.getResID())
			logService.logChange(save, oldResData,"1200", "RES", null);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return save;
	}
	
	public ReservationVO saveReservationData(ReservationVO resVO, ReservationVO resData)
	{
		ReservationVO save = repository.save(resVO);
		try {
			if(null!=save.getResID())
			logService.logChange(save, Optional.of(resData),"1200", "RES", null);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return save;
	}

	public List<ReservationVO> findReservationData(ReservationSearchDTO searchDTO) {
		return repository.findReservations(searchDTO);
	}

	public Optional<ReservationVO> getResData(Long resID) {
		return repository.findById(resID);
	}

	public List<ReservationVO> fetchReservationDataByStatus(String status)
	{
		if(status.equals("ALL"))
		{
			List<ReservationVO> reservations=repository.findAllStatusForToday(LocalDate.now(), LocalDate.now());
			return reservations;
		}
		return repository.findByStatusForToday(status,LocalDate.now(), LocalDate.now());
	}

	public List<ReservationVO> findReservationData(Map<String, Object> queryParam)
	{
		return repository.findReservations(queryParam);
	}

	public GuestCommInfo getGuestCommInfo(Long guestID)
	{
		return guestInfo_ServiceProxy.getGuestEmailCommInfo(guestID);
	}
	
	public ReservationVO updateModifiedFields(ReservationVO current, ReservationVO updated) {
		current.setStatus(updated.getStatus());
		if (!Objects.equals(current.getArriveDate(), updated.getArriveDate()))
		{
			current.setArriveDate(updated.getArriveDate());
		}
		if (!Objects.equals(current.getDeptDate(), updated.getDeptDate()))
		{
			current.setDeptDate(updated.getDeptDate());
		}
	    if (!Objects.equals(current.getFirstName(), updated.getFirstName())) {
	        current.setFirstName(updated.getFirstName());
	    }
	    if (!Objects.equals(current.getLastName(), updated.getLastName())) {
	        current.setFirstName(updated.getLastName());
	    }
	    return current;
	}


}
