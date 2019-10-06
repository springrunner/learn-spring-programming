package showcase.mvc.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * When using Spring Boot, you can enable @WebServlet, @WebFilter, and @WebListener
 * with the @ServletComponentScan annotation.
 */
@WebFilter(urlPatterns = "/*")
public class CustomeRequestLoggingFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UrlPathHelper urlPathHelper = new UrlPathHelper();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("init filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestUri = urlPathHelper.getRequestUri(httpRequest);
        String method = httpRequest.getMethod();

        log.debug("Before request [uri: {}, method: {}]", requestUri, method);
        chain.doFilter(httpRequest, httpResponse);
        log.debug("After request [uri: {}]", requestUri);
    }

    @Override
    public void destroy() {
        log.debug("destroy filter");
    }

}
