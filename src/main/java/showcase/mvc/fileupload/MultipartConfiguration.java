package showcase.mvc.fileupload;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

/**
 * customize servlet multipart config.
 */
@Configuration
public class MultipartConfiguration implements WebMvcConfigurer {

	/**
	 * tuning file upload limits.
	 *
	 * With Spring Boot,
	 * we can tune its auto-configured MultipartConfigElement with some property settings
	 * or register the bean manually.
	 *
	 * if you are not using Spring Boot, please see this page.
	 * https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-multipart-resolver-standard
	 *
	 * @see org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofMegabytes(10));
		factory.setMaxRequestSize(DataSize.ofMegabytes(10));

		return factory.createMultipartConfig();
	}
	
}
