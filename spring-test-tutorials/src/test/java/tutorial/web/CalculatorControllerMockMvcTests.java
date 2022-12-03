package tutorial.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringJUnitWebConfig(classes = CalculatorControllerMockMvcTests.Config.class)
class CalculatorControllerMockMvcTests {
	
	MockMvc mockMvc;

    @BeforeEach
    void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

	@Test
	void addition_command() throws Exception {		
		var request = MockMvcRequestBuilders
				.post("/calculate")
				.param("command", "add").param("first", "1").param("second", "2");
				
		this.mockMvc.perform(request).andExpectAll(
			MockMvcResultMatchers.status().isOk(),
			MockMvcResultMatchers.view().name("calculator"),
			MockMvcResultMatchers.model().attribute("message", "1 + 2 = 3")
        );
	}

	@Test
	void unknown_command() throws Exception {		
		var request = MockMvcRequestBuilders
				.post("/calculate")
				.param("command", "remove").param("first", "2").param("second", "1");
				
		this.mockMvc.perform(request).andExpectAll(
			MockMvcResultMatchers.status().isOk(),
			MockMvcResultMatchers.view().name("calculator"),
			MockMvcResultMatchers.model().attribute("message", "unknown command")
        );
	}
	
	@Test
	void page_not_found() throws Exception {		
		var request = MockMvcRequestBuilders.get("/calculator");
				
		this.mockMvc.perform(request).andExpectAll(
			MockMvcResultMatchers.status().isNotFound()			
        );
	}
	
	
	@Configuration
	static class Config {
		
		@Bean
		public CalculatorController calculatorController() {
			return new CalculatorController();
		}
	}
	
}
