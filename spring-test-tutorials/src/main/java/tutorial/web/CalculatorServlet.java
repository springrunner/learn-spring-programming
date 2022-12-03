package tutorial.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tutorial.Calculator;

@SuppressWarnings("serial")
public class CalculatorServlet extends HttpServlet {

	private final Calculator calculator = new Calculator();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var command = request.getParameter("command");
		var first = Integer.parseInt(request.getParameter("first"));
		var second = Integer.parseInt(request.getParameter("second"));
		
		var message = switch (command) {
			case "add" -> String.format("%d + %d = %d", first, second, calculator.add(first, second));
			default -> "unknown command";
		};
		
		response.setContentType("text/plain");
		response.getWriter().write(message);
	}

}
