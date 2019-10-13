package showcase.mvc.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

@Configuration
public class RenderingViewsConfiguration implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {    	
    	registry.viewResolver(new SimpleMappingViewResolver());
    	registry.order(10);
	}
    
	@Bean
    public BeanNameViewResolver beanNameViewResolver() {
        BeanNameViewResolver viewResolver = new BeanNameViewResolver();
        viewResolver.setOrder(20);
        
        return viewResolver;
    }

    @Bean
    public MustacheViewResolver mustacheViewResolver() {
        MustacheViewResolver viewResolver = new MustacheViewResolver();
        viewResolver.setPrefix("classpath:templates/");
        viewResolver.setSuffix(".mustache");
        viewResolver.setCharset("utf-8");
        viewResolver.setOrder(30);
        
        return viewResolver;
    }
    
    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(ContentNegotiationManager contentNegotiationManager) {
    	List<View> defaultViews = new ArrayList<>();
    	defaultViews.add(new MappingJackson2JsonView());
    	defaultViews.add(new MappingJackson2XmlView());
    	
    	ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
    	viewResolver.setContentNegotiationManager(contentNegotiationManager);
    	viewResolver.setDefaultViews(defaultViews);
    	
    	// ContentNegotiatingViewResolver uses all the other view resolvers to locate
		// a view so it should have a high precedence
    	viewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
    	
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
    
    static class TextView implements View {
    	
		@Override
        public String getContentType() {
            return MediaType.ALL_VALUE;
        }
    	
		@Override
		public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setContentType(MediaType.TEXT_PLAIN_VALUE);
			
			for (Entry<String, ?> entry : model.entrySet()) {
				String text = String.format("key: %s, value: %s", entry.getKey(), entry.getValue());
				response.getWriter().println(text);
			}
		}
    	
    }
    
    static class SimpleMappingViewResolver implements ViewResolver, Ordered {

		@Override
		public View resolveViewName(String viewName, Locale locale) throws Exception {
			if (Objects.equals("text", viewName)) {
				return new TextView();
			}
			return null;
		}

		@Override
		public int getOrder() {
			return 10;
		}
    	
    }

}
