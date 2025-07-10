package raicode.example.chatapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import raicode.example.chatapp.custom.customException.CustomException;
import raicode.example.chatapp.custom.customException.ResourceNotFoundException;
import raicode.example.chatapp.custom.customException.ServiceNotAvailableException;
import raicode.example.chatapp.dto.error.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), ex.getMessage(),
				request.getDescription(false).replace("uri=", ""), System.currentTimeMillis());
		return ResponseEntity.status(status).body(errorResponse);
	}

	@ExceptionHandler(ServiceNotAvailableException.class)
	public ResponseEntity<?> handleServiceError(ServiceNotAvailableException ex, WebRequest request) {
		HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
		ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), ex.getMessage(),
				request.getDescription(false).replace("uri=", ""), System.currentTimeMillis());
		return ResponseEntity.status(status).body(errorResponse);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> handleCustomException(CustomException ex, WebRequest request) {
		HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
		ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), ex.getMessage(),
				request.getDescription(false).replace("uri=", ""), System.currentTimeMillis());
		return ResponseEntity.status(status).body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenericException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), ex.getMessage(),
				request.getDescription(false).replace("uri=", ""), System.currentTimeMillis());
		return ResponseEntity.status(status).body("Unexpected error: " + errorResponse);

	}

}
