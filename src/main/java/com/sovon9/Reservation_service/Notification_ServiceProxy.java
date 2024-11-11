package com.sovon9.Reservation_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="Notification-service")
public interface Notification_ServiceProxy
{
	@PostMapping("/sendReservation-confirmation/to/{to}/name/{name}/resID/{resID}")
	public ResponseEntity<HttpStatus> sendReservationConfEmail(@PathVariable("to") String to,
			@PathVariable("name") String firstName, @PathVariable("name") Long resID);
}
