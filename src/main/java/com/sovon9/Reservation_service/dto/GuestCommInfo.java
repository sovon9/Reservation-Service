package com.sovon9.Reservation_service.dto;

import java.io.Serializable;

public class GuestCommInfo implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String email;
	private String phno;
	private Long resID;
	private Long guestID;
	private String action;
	private int roomNum;
	
	public GuestCommInfo()
	{
		super();
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPhno()
	{
		return phno;
	}
	public void setPhno(String phno)
	{
		this.phno = phno;
	}
	public Long getResID()
	{
		return resID;
	}
	public void setResID(Long resID)
	{
		this.resID = resID;
	}
	public Long getGuestID()
	{
		return guestID;
	}
	public void setGuestID(Long guestID)
	{
		this.guestID = guestID;
	}
	public String getAction()
	{
		return action;
	}
	public void setAction(String action)
	{
		this.action = action;
	}
	public int getRoomNum()
	{
		return roomNum;
	}
	public void setRoomNum(int roomNum)
	{
		this.roomNum = roomNum;
	}
	
}
