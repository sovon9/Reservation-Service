package com.sovon9.Reservation_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sovon9.Reservation_service.log.service.LogService;
import com.sovon9.Reservation_service.model.ReservationVO;
import com.sovon9.Reservation_service.repository.ReservationRepository;
import com.sovon9.Reservation_service.service.ReservationService;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
class ReservationServiceApplicationTests {

	@Mock
	ReservationRepository repository;
	@Mock
	LogService logService;
	
	@InjectMocks
	ReservationService service;
	
	ReservationVO reservationVO;
	
	@BeforeEach
	void contextLoads() {
		reservationVO = new ReservationVO();
		reservationVO.setResID(1L);
	}
	
	@Test
	public void testSaveReservationData()
	{
		when(repository.save(eq(reservationVO))).thenReturn(reservationVO);
		
		ReservationVO saveReservationData = service.saveReservationData(reservationVO);
		
		assertNotNull(saveReservationData);
		assertEquals(1L, saveReservationData.getResID());
		verify(repository).save(reservationVO);
	}
	
	@Test
	public void testLogChnage() throws JsonProcessingException
	{
		when(repository.save(eq(reservationVO))).thenReturn(reservationVO);
		doThrow(new JsonProcessingException("") {}).when(logService).logChange(any(), any(), null, null, null);
		
		ReservationVO saveReservationData = service.saveReservationData(reservationVO);
		
		assertNotNull(saveReservationData);
		assertEquals(1L, saveReservationData.getResID());
		verify(repository).save(reservationVO);
	}

}
