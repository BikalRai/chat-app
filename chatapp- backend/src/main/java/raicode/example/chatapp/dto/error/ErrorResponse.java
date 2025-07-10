package raicode.example.chatapp.dto.error;

public class ErrorResponse {

	private int status;
	private String error;
	private String message;
	private String path;
	private Long timeStamp;

	public ErrorResponse(int status, String error, String message, String path, Long timeStamp) {
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
