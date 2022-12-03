package tutorial.web;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;

class CalculatorServletTest {

	private final CalculatorServlet calculatorServlet = createCalculatorServlet();
	
	private CalculatorServlet createCalculatorServlet() {
		try {
			var calculatorServlet = new CalculatorServlet();
			calculatorServlet.init(new MockServletConfig(new MockServletContext(), "calculator-servlet"));
			return calculatorServlet;
		} catch (ServletException error) {
			throw new IllegalStateException(error);
		}
	}
	
	@Test
	void addition_command() throws ServletException, IOException {		
		var request = new MockHttpServletRequest(calculatorServlet.getServletContext()); {
			request.addParameter("command", "add");
			request.addParameter("first", "1");
			request.addParameter("second", "2");
		}
		var response = new MockHttpServletResponse();
		
		calculatorServlet.service(request, response);
		
		Assertions.assertEquals("text/plain", response.getContentType());
		Assertions.assertEquals("1 + 2 = 3", response.getContentAsString());		
	}
	
	@Test
	void unknown_command() throws ServletException, IOException {		
		var request = new MockHttpServletRequest(calculatorServlet.getServletContext()); {
			request.addParameter("command", "remove");
			request.addParameter("first", "2");
			request.addParameter("second", "1");
		}
		var response = new MockHttpServletResponse();
			
		calculatorServlet.service(request, response);
		
		Assertions.assertEquals("unknown command", response.getContentAsString());		
	}

}
