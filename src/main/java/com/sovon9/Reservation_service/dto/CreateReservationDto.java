package com.sovon9.Reservation_service.dto;

import java.util.Date;

public class CreateReservationDto {

	private Long guestID;
	private String firstName;
	private String lastName;
	private Date createDate;
	private String status;
	private Date arriveDateTime;
	private Date deptDateTime;
	private String paymentType;
	private RoomDto roomDto;
	public Long getGuestID() {
		return guestID;
	}
	public void setGuestID(Long guestID) {
		this.guestID = guestID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getArriveDateTime() {
		return arriveDateTime;
	}
	public void setArriveDateTime(Date arriveDateTime) {
		this.arriveDateTime = arriveDateTime;
	}
	public Date getDeptDateTime() {
		return deptDateTime;
	}
	public void setDeptDateTime(Date deptDateTime) {
		this.deptDateTime = deptDateTime;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public RoomDto getRoomDto() {
		return roomDto;
	}
	public void setRoomDto(RoomDto roomDto) {
		this.roomDto = roomDto;
	}
	
}
