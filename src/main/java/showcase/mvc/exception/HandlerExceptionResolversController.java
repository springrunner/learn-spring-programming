package showcase.mvc.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exceptions")
public class HandlerExceptionResolversController {

	@GetMapping("/exception-handler")
	public void exceptionHandler() {
		throw new IllegalStateException("Sorry!");
	}

	@GetMapping("/global-exception-handler")
	public void globalExceptionHandler() throws GlobalException {
		throw new GlobalException();
	}

	@GetMapping("/response-status")
	public void responseStatus() {
		throw new ConflictException();
	}

	@ExceptionHandler
	public String handle(IllegalStateException error) {
		return "IllegalStateException handled!";
	}

	@GetMapping("/mapping")
	public void mapping() {
		// See showcase.mvc.exception.ExceptionHandlingConfiguration#simpleMappingExceptionResolver

		throw new MappedException();
	}

}
