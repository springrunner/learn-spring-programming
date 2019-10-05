package showcase.mvc.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

	@GetMapping("/exception")
	public String exception() {
		throw new IllegalStateException("Sorry!");
	}

	@GetMapping("/global-exception")
	public String businessException() throws BusinessException {
		throw new BusinessException();
	}

	@ExceptionHandler
	public String handle(IllegalStateException error) {
		return "IllegalStateException handled!";
	}

}
