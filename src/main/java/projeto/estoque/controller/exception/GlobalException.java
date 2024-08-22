package projeto.estoque.controller.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleBusinessException(IllegalArgumentException businessException){
		return new ResponseEntity<String>(businessException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNotFoundException(NoSuchElementException notFoundException){
		return new ResponseEntity<String>(notFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> handleUnexpectedException(Throwable unexpectedException){
		return new ResponseEntity<String>(unexpectedException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
