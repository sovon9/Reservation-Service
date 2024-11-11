package com.sovon9.Reservation_service.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sovon9.Reservation_service.log.ChangeLog;

@Repository
public interface LogRepository extends JpaRepository<ChangeLog, Long>{

}
