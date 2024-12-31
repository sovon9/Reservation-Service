package com.sovon9.Reservation_service.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutoRoomStatusChangeJob
{
	@Scheduled(cron = "* * * * * *")
	public void performRoomStatusChange()
	{
		System.out.println("running ..........");
	}
}
