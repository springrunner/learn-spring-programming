package showcase.mvc.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/responses")
public class GeneratingResponsesController {

	@GetMapping("/annotation")
    @ResponseBody
	public String responseBody() {
		return "The String ResponseBody";
	}

	@GetMapping("/charset/accept")
    @ResponseBody
	public String responseAcceptHeaderCharset() {
		return "\uc548\ub155\u0020\uc138\uacc4\uc57c\u0021 (\"Hello world!\" in Korean)";
	}

	@GetMapping(value="/charset/produce", produces="text/plain;charset=UTF-8")
    @ResponseBody
	public String responseProducesConditionCharset() {
		return "\uc548\ub155\u0020\uc138\uacc4\uc57c\u0021 (\"Hello world!\" in Korean)";
	}

	@GetMapping("/entity/status")
	public ResponseEntity<String> responseEntityStatusCode() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
							 .body("The String ResponseBody with custom status code (403 Forbidden)");
	}

	@GetMapping("/entity/headers")
	public ResponseEntity<String> responseEntityCustomHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return ResponseEntity.ok()
							 .headers(headers)
							 .body("The String ResponseBody with custom header Content-Type=text/plain");
	}

}
