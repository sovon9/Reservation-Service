package com.sovon9.Reservation_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sovon9.Reservation_service.dto.GuestCommInfo;

@FeignClient(name="GuestInfo-service", url = "localhost:8085", path = "/guestinfo-service")
public interface GuestInfo_ServiceProxy
{
	@GetMapping("/guestinfo/guestCommInfo/{guestID}")
	public GuestCommInfo getGuestEmailCommInfo(@PathVariable("guestID") Long guestID);
}
