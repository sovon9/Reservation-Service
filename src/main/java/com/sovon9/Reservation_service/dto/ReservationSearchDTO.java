package com.sovon9.Reservation_service.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class ReservationSearchDTO {

	private Long rrID;
	private Long resID;
	private Long guestID;
	private String firstName;
	private String lastName;
	private Date createDate;
	private String status;
	private LocalDate arriveDate;
	private LocalTime arriveTime;
	private LocalDate deptDate;
	private LocalTime deptTime;
	private String paymentType;
	private Integer roomnum;
	public ReservationSearchDTO() {
		super();
	}
	
	public ReservationSearchDTO(Long rrID, Long resID, Long guestID, String firstName, String lastName, Date createDate,
			String status, LocalDate arriveDate, LocalTime arriveTime, LocalDate deptDate, LocalTime deptTime,
			String paymentType, Integer roomnum)
	{
		super();
		this.rrID = rrID;
		this.resID = resID;
		this.guestID = guestID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.createDate = createDate;
		this.status = status;
		this.arriveDate = arriveDate;
		this.arriveTime = arriveTime;
		this.deptDate = deptDate;
		this.deptTime = deptTime;
		this.paymentType = paymentType;
		this.roomnum = roomnum;
	}

	public Long getRrID() {
		return rrID;
	}
	public void setRrID(Long rrID) {
		this.rrID = rrID;
	}
	public Long getResID() {
		return resID;
	}
	public void setResID(Long resID) {
		this.resID = resID;
	}
	public Long getGuestID() {
		return guestID;
	}
	public void setGuestID(Long guestID) {
		this.guestID = guestID;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
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
	public LocalDate getArriveDate()
	{
		return arriveDate;
	}
	public void setArriveDate(LocalDate arriveDate)
	{
		this.arriveDate = arriveDate;
	}
	public LocalTime getArriveTime()
	{
		return arriveTime;
	}
	public void setArriveTime(LocalTime arriveTime)
	{
		this.arriveTime = arriveTime;
	}
	public LocalDate getDeptDate()
	{
		return deptDate;
	}
	public void setDeptDate(LocalDate deptDate)
	{
		this.deptDate = deptDate;
	}
	public LocalTime getDeptTime()
	{
		return deptTime;
	}
	public void setDeptTime(LocalTime deptTime)
	{
		this.deptTime = deptTime;
	}
	public String getPaymentType()
	{
		return paymentType;
	}
	public void setPaymentType(String paymentType)
	{
		this.paymentType = paymentType;
	}
	public Integer getRoomnum()
	{
		return roomnum;
	}
	public void setRoomnum(Integer roomnum)
	{
		this.roomnum = roomnum;
	}
	
}
