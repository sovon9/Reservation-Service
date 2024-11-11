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
	
}
