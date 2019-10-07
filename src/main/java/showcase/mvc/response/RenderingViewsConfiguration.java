package showcase.mvc.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Configuration
public class RenderingViewsConfiguration {

    @Bean
    public BeanNameViewResolver beanNameViewResolver() {
        BeanNameViewResolver viewResolver = new BeanNameViewResolver();

        // Set the order to run before the ThymeleafViewResolver registered by Spring Boot
        viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE - 15);

        return viewResolver;
    }

    @Bean
    public MustacheViewResolver mustacheViewResolver() {
        MustacheViewResolver viewResolver = new MustacheViewResolver();
        viewResolver.setPrefix("classpath:templates/");
        viewResolver.setSuffix(".mustache");
        viewResolver.setCharset("utf-8");

        // Set the order to run before the ThymeleafViewResolver registered by Spring Boot
        viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);

        return viewResolver;
    }

    @Bean("delegateDataBindingView")
    public View delegateDataBindingView(MustacheViewResolver mustacheViewResolver) throws Exception {
        return new DelegateView(mustacheViewResolver.resolveViewName("views/dataBinding", null));
    }


    static class DelegateView implements View {

        private final Logger log = LoggerFactory.getLogger(this.getClass());
        private final View delegate;

        public DelegateView(View view) {
            this.delegate = view;
        }

        @Override
        public String getContentType() {
            return MediaType.TEXT_HTML_VALUE;
        }

        @Override
        public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
            log.debug("delegating render: {}", delegate);
            delegate.render(model, request, response);
        }

    }

}
