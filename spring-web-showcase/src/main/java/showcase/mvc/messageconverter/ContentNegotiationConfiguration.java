package showcase.mvc.messageconverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ContentNegotiationConfiguration implements WebMvcConfigurer {
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {		
		// Accept 헤더 전략 설정
		configurer.ignoreAcceptHeader(false);
		
		// URL 경로 확장자 전략 설정
		configurer.favorPathExtension(true);
		
		// 쿼리 파라메터 전략 설정
		configurer.favorParameter(true);
		
		// URL 경로 확장자 또는 쿼리 파라메터 전략 사용지 미디어타입 맵핑 규칙  
		configurer.mediaType("json", MediaType.APPLICATION_JSON);
		configurer.mediaType("xml", MediaType.APPLICATION_XML);
		
		// 협상 전략을 직접 구성
		// configurer.strategies(strategies);
	}

	@Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
		// URL 경로 확장자 전략 사용시 함께 활성화 필요
        configurer.setUseRegisteredSuffixPatternMatch(true);
    }	

}
