package tutorial.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HelloSpringBootApplication {
	
    @RequestMapping("/")
    String home() {
        return "Hello, Spring Boot!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloSpringBootApplication.class, args);
    }

}