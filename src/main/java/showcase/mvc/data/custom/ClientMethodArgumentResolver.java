package showcase.mvc.data.custom;

import java.io.IOException;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import ua_parser.Client;
import ua_parser.Parser;

public class ClientMethodArgumentResolver implements HandlerMethodArgumentResolver {

	private final Parser parser;
	
	public ClientMethodArgumentResolver() {
		try {
			this.parser = new Parser();
		} catch (IOException error) {
			throw new IllegalStateException(error);
		}
	}
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return Client.class.equals(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return parser.parse(webRequest.getHeader(HttpHeaders.USER_AGENT));
	}

}
