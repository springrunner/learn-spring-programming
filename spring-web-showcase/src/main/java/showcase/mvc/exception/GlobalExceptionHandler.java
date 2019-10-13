package showcase.mvc.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public String handleGlobalException(GlobalException error) {
		return "GlobalException handled!";
	}

}
