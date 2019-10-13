package showcase.mvc.exception;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@Configuration
public class ExceptionHandlingConfiguration implements WebMvcConfigurer, ErrorPageRegistrar {

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		// HandlerExceptionResolver를 직접 구성할 때 사용 
	}

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		// HandlerExceptionResolver 기본 전략 구성외 추가 전략을 구성할 때 사용
		// 스프링 4.3 이후 사용 가능
		resolvers.add(creeateSimpleMappingExceptionResolver());
	}

	protected SimpleMappingExceptionResolver creeateSimpleMappingExceptionResolver() {
        Properties mappings = new Properties();
        mappings.put(MappedException.class.getName(), "mappedExceptionView");

        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        exceptionResolver.setExceptionMappings(mappings);

        return exceptionResolver;
    }

    @Bean
    public View mappedExceptionView() {
        return new View() {

            @Override
            public String getContentType() {
                return MediaType.ALL_VALUE;
            }

            @Override
            public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
                Exception exception = (Exception) model.get("exception");
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.getWriter().println(String.format("%s handled!", exception.getClass().getSimpleName()));
                response.flushBuffer();
            }

        };
    }

    // Container Error Page
    //
    // to customize the default error page of the container, you can declare an error page mapping in web.xml.
    // the Servlet API does not provide a way to create error page mappings in Java.
    // but you can be registered via ErrorPageRegistrar component when using Spring Boot embedded servlet container

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        registry.addErrorPages(new ErrorPage(ContainerErrorPageException.class, "/exceptions/container-error-page/handle"));
    }

}
