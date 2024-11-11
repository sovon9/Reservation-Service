package com.sovon9.Reservation_service.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sovon9.Reservation_service.model.ReservationVO;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationVO, Long>,ReservationRepositoryCustom{

	List<ReservationVO> findByStatus(String status);

	List<ReservationVO> findReservations(Map<String, Object> queryParam);

}
