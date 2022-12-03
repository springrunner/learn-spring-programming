package tutorial.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tutorial.Calculator;

@Controller
public class CalculatorController {

	private final Calculator calculator = new Calculator();
	
	@RequestMapping("/calculate")
	public ModelAndView calculate(CalculateCommand command) {
		var message = switch (command.command) {
			case "add" -> String.format("%d + %d = %d", command.first, command.second, calculator.add(command.first, command.second));
			default -> "unknown command";
		};
	
		return new ModelAndView("calculator", "message", message);
	}
	
	
	static record CalculateCommand(String command, Integer first, Integer second) { }
}
