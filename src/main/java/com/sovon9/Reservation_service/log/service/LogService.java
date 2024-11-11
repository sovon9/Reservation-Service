package com.sovon9.Reservation_service.log.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sovon9.Reservation_service.log.ChangeLog;
import com.sovon9.Reservation_service.log.repository.LogRepository;
import com.sovon9.Reservation_service.model.ReservationVO;

@Service
public class LogService {

	@Autowired
	private LogRepository logRepository;

	public void logChange(ReservationVO newResVO, ReservationVO oldResVO, String userID, String changeType, String desc) throws JsonProcessingException
	{
	    LocalDateTime logtime = LocalDateTime.now();
	    if (null != newResVO && null != oldResVO) 
		{
			if (!newResVO.getFirstName().equals(oldResVO.getFirstName()))
			{
			    ChangeLog changeLog = new ChangeLog();
				StringBuilder from = new StringBuilder("FirstName : [");
				from.append(oldResVO.getFirstName());
				from.append("]");
				changeLog.setOldValue(from.toString());
				StringBuilder to = new StringBuilder("FirstName : [");
				to.append(newResVO.getFirstName());
				to.append("]");
				changeLog.setNewValue(to.toString());
				logRepository.save(changeLog);
			}
			
			if (!newResVO.getLastName().equals(oldResVO.getLastName()))
			{
			    ChangeLog changeLog = new ChangeLog();
				StringBuilder from = new StringBuilder("LastName : [");
				from.append(oldResVO.getLastName());
				from.append("]");
				changeLog.setOldValue(from.toString());
				StringBuilder to = new StringBuilder("LastName : [");
				to.append(newResVO.getLastName());
				to.append("]");
				changeLog.setNewValue(to.toString());
				logRepository.save(changeLog);
			}
			
			if (!newResVO.getCreateDate().equals(oldResVO.getCreateDate()))
			{
			    ChangeLog changeLog = new ChangeLog();
				StringBuilder from = new StringBuilder("CreateDate : [");
				from.append(oldResVO.getCreateDate());
				from.append("]");
				changeLog.setOldValue(from.toString());
				StringBuilder to = new StringBuilder("CreateDate : [");
				to.append(newResVO.getCreateDate());
				to.append("]");
				changeLog.setNewValue(to.toString());
				logRepository.save(changeLog);
			}
			
			if (!newResVO.getStatus().equals(oldResVO.getStatus()))
			{
			    ChangeLog changeLog = new ChangeLog();
				StringBuilder from = new StringBuilder("Status : [");
				from.append(oldResVO.getStatus());
				from.append("]");
				changeLog.setOldValue(from.toString());
				StringBuilder to = new StringBuilder("Status : [");
				to.append(newResVO.getStatus());
				to.append("]");
				changeLog.setNewValue(to.toString());
				logRepository.save(changeLog);
			}
			
			if (!newResVO.getArriveDate().equals(oldResVO.getArriveDate()))
			{
			    ChangeLog changeLog = new ChangeLog();
				StringBuilder from = new StringBuilder("Arrival Date : [");
				from.append(oldResVO.getArriveDate());
				from.append("]");
				changeLog.setOldValue(from.toString());
				StringBuilder to = new StringBuilder("Arrival Date : [");
				to.append(newResVO.getArriveDate());
				to.append("]");
				changeLog.setNewValue(to.toString());
				logRepository.save(changeLog);
			}
			
			if (!newResVO.getArriveTime().equals(oldResVO.getArriveTime()))
			{
			    ChangeLog changeLog = new ChangeLog();
				StringBuilder from = new StringBuilder("Arrival Time : [");
				from.append(oldResVO.getArriveTime());
				from.append("]");
				changeLog.setOldValue(from.toString());
				StringBuilder to = new StringBuilder("Arrival Time : [");
				to.append(newResVO.getArriveTime());
				to.append("]");
				changeLog.setNewValue(to.toString());
				logRepository.save(changeLog);
			}
			
			if (!newResVO.getDeptDate().equals(oldResVO.getDeptDate()))
			{
			    ChangeLog changeLog = new ChangeLog();
				StringBuilder from = new StringBuilder("Departure Date : [");
				from.append(oldResVO.getDeptDate());
				from.append("]");
				changeLog.setOldValue(from.toString());
				StringBuilder to = new StringBuilder("Departure Date : [");
				to.append(newResVO.getDeptDate());
				to.append("]");
				changeLog.setNewValue(to.toString());
				logRepository.save(changeLog);
			}
			if (!newResVO.getDeptTime().equals(oldResVO.getDeptTime()))
			{
			    ChangeLog changeLog = new ChangeLog();
				StringBuilder from = new StringBuilder("Departure Time : [");
				from.append(oldResVO.getDeptTime());
				from.append("]");
				changeLog.setOldValue(from.toString());
				StringBuilder to = new StringBuilder("Departure Time : [");
				to.append(newResVO.getDeptTime());
				to.append("]");
				changeLog.setNewValue(to.toString());
				logRepository.save(changeLog);
			}
		}
	}
}
