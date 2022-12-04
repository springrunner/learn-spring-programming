package examples.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ServletWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServletWebApplication.class, args);
  }

}
