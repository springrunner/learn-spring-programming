package tutorial.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.function.BodyInserters;

@SpringJUnitWebConfig(classes = CalculatorControllerWebTestClientTests.Config.class)
class CalculatorControllerWebTestClientTests {

	WebTestClient client;

	@BeforeEach
	void setup(WebApplicationContext wac) {
		this.client = MockMvcWebTestClient.bindToApplicationContext(wac).build();
	}

	@Test
	void addition_command() throws Exception {
		var formData = new LinkedMultiValueMap<String, String>(); {
			formData.add("command", "add");
			formData.add("first", "1");
			formData.add("second", "2");
		}

		var body = BodyInserters.fromFormData(formData);
		var resposne = this.client.post().uri("/calculate").body(body).exchange().expectAll(
				spec -> spec.expectStatus().isOk()
		);

		MockMvcWebTestClient.resultActionsFor(resposne.expectBody().returnResult()).andExpectAll(
			MockMvcResultMatchers.view().name("calculator"),
			MockMvcResultMatchers.model().attribute("message", "1 + 2 = 3"));
	}

	@Test
	void page_not_found() throws Exception {
		this.client.post().uri("/calculator").exchange().expectStatus().isNotFound();
	}

	@Configuration
	static class Config {

		@Bean
		public CalculatorController calculatorController() {
			return new CalculatorController();
		}
	}

}
