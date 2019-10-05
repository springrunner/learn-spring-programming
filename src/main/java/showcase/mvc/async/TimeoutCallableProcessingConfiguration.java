package showcase.mvc.async;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TimeoutCallableProcessingConfiguration implements WebMvcConfigurer {

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        // The timeout is configured via Spring Boot properties. (spring.mvc.async.request-timeout)
        // configurer.setDefaultTimeout(60000);

        configurer.registerCallableInterceptors(new TimeoutCallableProcessingInterceptor());
    }

}
