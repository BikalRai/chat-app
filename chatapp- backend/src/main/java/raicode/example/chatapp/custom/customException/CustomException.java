package raicode.example.chatapp.custom.customException;

public class CustomException extends RuntimeException {

	public CustomException() {
		super();
	}
	
	public CustomException(String message) {
		super(message);
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}
}
