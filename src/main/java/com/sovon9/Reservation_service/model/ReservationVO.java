package com.sovon9.Reservation_service.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="res")
public class ReservationVO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2231968940133452008L;
	@Column
	private Long pmsID;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long resID;
	@Column
	private Long guestID;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private LocalDate createDate;
	@Column
	private String status;
	@Column
	private LocalDate arriveDate;
	@Column
	private LocalTime arriveTime;
	@Column
	private LocalDate deptDate;
	@Column
	private LocalTime deptTime;
	@Column
	private String paymentType;
	@Column
	private Integer roomnum;
	public ReservationVO() {
		super();
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(LocalDate createDate)
	{
		this.createDate = createDate;
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
	public Long getRrID() {
		return pmsID;
	}
	public void setRrID(Long rrID) {
		this.pmsID = rrID;
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
	
	public Long getPmsID() {
		return pmsID;
	}
	public void setPmsID(Long pmsID) {
		this.pmsID = pmsID;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
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
	@Override
	public int hashCode()
	{
		return Objects.hash(arriveDate, arriveTime, createDate, deptDate, deptTime, firstName, guestID, lastName,
				paymentType, pmsID, resID, roomnum, status);
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservationVO other = (ReservationVO) obj;
		return Objects.equals(arriveDate, other.arriveDate) && Objects.equals(arriveTime, other.arriveTime)
				&& Objects.equals(createDate, other.createDate) && Objects.equals(deptDate, other.deptDate)
				&& Objects.equals(deptTime, other.deptTime) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(guestID, other.guestID) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(paymentType, other.paymentType) && Objects.equals(pmsID, other.pmsID)
				&& Objects.equals(resID, other.resID) && Objects.equals(roomnum, other.roomnum)
				&& Objects.equals(status, other.status);
	}
	
}
