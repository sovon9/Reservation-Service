package com.sovon9.Reservation_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ReservationServiceApplication {

	public static void main(String[] args) {
		try
		{
		SpringApplication.run(ReservationServiceApplication.class, args);
		}
		catch(Exception e)
		{
			if (e.getClass().getName().equals("SilentExitException"))
			{
				// don't do anything
			}
		}
	}

}
