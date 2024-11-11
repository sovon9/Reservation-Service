package com.sovon9.Reservation_service.log;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class ChangeLog {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column
    private Long reservationId;
	@Column
    private String changedBy;
	@Column
    private String changeType;
	@Column
    private LocalDateTime changeTimestamp;
	@Column
    private String oldValue;
	@Column
    private String newValue;
	@Column
    private String changeDescription;
	@Column
	private LocalDateTime logTime;
	public ChangeLog() {
		super();
	}
	
	public ChangeLog(Long reservationId, String changedBy, String changeType, LocalDateTime changeTimestamp,
			String oldValue, String newValue, String changeDescription, LocalDateTime logTime) {
		super();
		this.reservationId = reservationId;
		this.changedBy = changedBy;
		this.changeType = changeType;
		this.changeTimestamp = changeTimestamp;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.changeDescription = changeDescription;
		this.logTime = logTime;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	public String getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	public LocalDateTime getChangeTimestamp() {
		return changeTimestamp;
	}
	public void setChangeTimestamp(LocalDateTime changeTimestamp) {
		this.changeTimestamp = changeTimestamp;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public String getChangeDescription() {
		return changeDescription;
	}
	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}
	public LocalDateTime getLogTime() {
		return logTime;
	}
	public void setLogTime(LocalDateTime logTime) {
		this.logTime = logTime;
	}
	
}
