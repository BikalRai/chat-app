package raicode.example.chatapp.custom.customException;

public class ServiceNotAvailableException extends CustomException {
	
	public ServiceNotAvailableException () {
		super();
	}
	
	public ServiceNotAvailableException (String message) {
		super(message);
	}

	public ServiceNotAvailableException (String message, Throwable cause) {
		super(message, cause);
	}

}
