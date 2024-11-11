package com.sovon9.Reservation_service.exception;

public class PMSException extends RuntimeException
{
	
	private static final long serialVersionUID = 1L;
	private String status;
	public PMSException(String message, String status)
	{
		super(message);
		this.status=status;
	}

	public PMSException(String message)
	{
		super(message);
	}

	public String getStatus()
	{
		return status;
	}

}
