package tutorial.web;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.ModelAndViewAssert;

class CalculatorControllerTest {
	
	private final CalculatorController controller = new CalculatorController();

	@Test
	void addition_command() throws ServletException, IOException {		
		var command = new CalculatorController.CalculateCommand("add", 1, 2);
		
		var mav = controller.calculate(command);
		
		ModelAndViewAssert.assertViewName(mav, "calculator");
		ModelAndViewAssert.assertModelAttributeValue(mav, "message", "1 + 2 = 3");
	}
	
	@Test
	void unknown_command() throws ServletException, IOException {		
		var command = new CalculatorController.CalculateCommand("remove", 2, 1);
		
		var mav = controller.calculate(command);
		
		ModelAndViewAssert.assertAndReturnModelAttributeOfType(mav, "message", String.class);
		ModelAndViewAssert.assertModelAttributeValue(mav, "message", "unknown command");		
	}

}
