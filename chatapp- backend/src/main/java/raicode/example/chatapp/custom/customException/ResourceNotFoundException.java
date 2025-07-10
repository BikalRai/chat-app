package raicode.example.chatapp.custom.customException;

public class ResourceNotFoundException extends CustomException {
	
	public ResourceNotFoundException () {
		super();
	}
	
	public ResourceNotFoundException (String message) {
		super(message);
	}

	public ResourceNotFoundException (String message, Throwable cause) {
		super(message, cause);
	}

}
