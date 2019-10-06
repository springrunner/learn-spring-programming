package showcase.mvc.filter;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Collections;
import java.util.EnumSet;

@Configuration
public class FiltersConfiguration implements WebApplicationInitializer, ServletContextInitializer {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /* Servlet Context Initialization
     * Use WebApplicationInitializer to deploy servlet container via war packaging
     * Use ServletContextInitializer if you are using an embedded servlet container
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic registration = servletContext.addFilter(
                "customRequestLoggingFilter", CustomeRequestLoggingFilter.class);
        registration.setAsyncSupported(true);
        registration.addMappingForUrlPatterns(getDispatcherTypes(), false, "/*");
        log.debug("Registered CustomRequestLoggingFilter [urlPatterns: /*]");
    }

    @Bean
    public FilterRegistrationBean<MDCInsertingServletFilter> mdcInsertingServletFilter() {
        FilterRegistrationBean<MDCInsertingServletFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new MDCInsertingServletFilter());
        filterRegistrationBean.setAsyncSupported(true);
        filterRegistrationBean.setDispatcherTypes(getDispatcherTypes());
        filterRegistrationBean.setMatchAfter(false);
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
        log.debug("Registered MDCInsertingServletFilter [urlPatterns: /*]");

        return filterRegistrationBean;
    }

    protected EnumSet<DispatcherType> getDispatcherTypes() {
        return EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC);
    }

}
