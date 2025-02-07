package com.sovon9.Reservation_service.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sovon9.Reservation_service.model.ReservationVO;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationVO, Long>,ReservationRepositoryCustom{

	List<ReservationVO> findByStatus(String status);

	List<ReservationVO> findReservations(Map<String, Object> queryParam);

	@Query(nativeQuery = true, value = "select * from res where arrive_date=?1 or dept_date=?2")
	List<ReservationVO> findAllStatusForToday(LocalDate arriveDate, LocalDate departDate);
	
	@Query(nativeQuery = true, value = "select * from res where status=?1 and arrive_date=?2 or dept_date=?3")
	List<ReservationVO> findByStatusForToday(String status, LocalDate arriveDate, LocalDate departDate);

}
