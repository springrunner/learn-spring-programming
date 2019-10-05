package showcase.mvc.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/async/deferred-result")
public class DeferredResultController {

	private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

	@GetMapping("/response-body")
	public @ResponseBody DeferredResult<String> deferredResult() {
		final DeferredResult<String> deferredResult = new DeferredResult<>();
		executor.schedule(() -> deferredResult.setResult("Deferred result"), 2, TimeUnit.SECONDS);
		return deferredResult;
	}

	@GetMapping("/model-and-view")
	public DeferredResult<ModelAndView> deferredResultWithView() {
		final DeferredResult<ModelAndView> deferredResult = new DeferredResult<>();
		executor.schedule(() -> deferredResult.setResult(new ModelAndView("async/deferredResult", "javaBean", new JavaBean("bar", "apple"))), 2, TimeUnit.SECONDS);
		return deferredResult;
	}

	@GetMapping("/exception")
	public @ResponseBody DeferredResult<String> deferredResultWithException() {
		final DeferredResult<String> deferredResult = new DeferredResult<>();
		executor.schedule(() -> deferredResult.setErrorResult(new IllegalStateException("DeferredResult error")), 1, TimeUnit.SECONDS);
		return deferredResult;
	}

	@GetMapping("/timeout-value")
	public @ResponseBody DeferredResult<String> deferredResultWithTimeoutValue() {
		// Provide a default result in case of timeout and override the timeout value
		// set in src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml

		return new DeferredResult<>(1000L, "Deferred result after timeout");
	}

	@ExceptionHandler
	@ResponseBody
	public String handleException(IllegalStateException ex) {
		return "Handled exception: " + ex.getMessage();
	}

}
