package tutorial.boot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloSpringBootApplicationTests {
		
	@Test
    void helloSpringBoot(@LocalServerPort int port) {
		var client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
				
		client.get().uri("/").exchange().expectAll(
    		spec -> spec.expectStatus().isOk(),
    		spec -> spec.expectBody(String.class).isEqualTo("Hello, Spring Boot!")
    	);
    }

}
