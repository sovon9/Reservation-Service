package com.sovon9.Reservation_service.exception;

/**
 * provides exception structure for Exception
 * @author Sovon Singha
 *
 */
public class PMSErrorResponse {

	private String status;
	private String message;
	private long timestamp;
	public PMSErrorResponse() {
		super();
	}
	
	public PMSErrorResponse(String status, String message, long timestamp)
	{
		super();
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "PMSErrorResponse [status=" + status + ", message=" + message + ", timestamp=" + timestamp + "]";
	}
	
}
