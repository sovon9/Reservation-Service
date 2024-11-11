package com.sovon9.Reservation_service.service;

import java.util.List;
import java.util.Map;
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
		try {
			if(null!=resVO.getResID())
			logService.logChange(resVO, getResData(resVO.getResID()).orElse(null),"1200", "RES", null);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return repository.save(resVO);
	}

	public List<ReservationVO> findReservationData(ReservationSearchDTO searchDTO) {
		return repository.findReservations(searchDTO);
	}

	public Optional<ReservationVO> getResData(Long resID) {
		return repository.findById(resID);
	}

	public List<ReservationVO> fetchReservationDataByStatus(String status)
	{
		return repository.findByStatus(status);
	}

	public List<ReservationVO> findReservationData(Map<String, Object> queryParam)
	{
		return repository.findReservations(queryParam);
	}

	public GuestCommInfo getGuestCommInfo(Long guestID)
	{
		return guestInfo_ServiceProxy.getGuestEmailCommInfo(guestID);
		
	}
	
}
