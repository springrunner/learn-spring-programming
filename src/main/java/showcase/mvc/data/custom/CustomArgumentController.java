package showcase.mvc.data.custom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import ua_parser.Client;
import ua_parser.Parser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomArgumentController {

	@ModelAttribute
	void beforeInvokingHandlerMethod(HttpServletRequest request) {
		request.setAttribute("foo", "bar");
	}
	
	@GetMapping("/data/custom/request-attribute")
	public String custom(@RequestAttribute("foo") String foo) {
		return "Got 'foo' request attribute value '" + foo + "'";
	}

	@GetMapping("/data/custom/parsing-client")
	public String custom(HttpServletRequest request) throws IOException {
		String userAgent = request.getHeader("User-Agent");
		Client client = new Parser().parse(userAgent);
				
		return "Parsing client '" + client + "'";
	}
	
	@GetMapping("/data/custom/client")
	public String custom(Client client) {
		return "Parsing client '" + client + "'";
	}
	
}
