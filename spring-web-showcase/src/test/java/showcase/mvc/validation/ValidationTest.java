package showcase.mvc.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ValidationTest.Config.class)
public class ValidationTest {

	@Autowired javax.validation.Validator validator;
	@Autowired org.springframework.validation.SmartValidator smartValidator;
	
	@Test
	public void usingValidator() {
		JavaBean bean = new JavaBean(1, LocalDate.now());
		Set<ConstraintViolation<JavaBean>> violations = null;
		
		violations = validator.validate(bean);
		
		assertThat(violations).hasSize(1);
	}
	
	@Test
	public void usingSmartValidator() {
		JavaBean bean = new JavaBean(1, LocalDate.now().plusDays(1));
		Errors errors = new BeanPropertyBindingResult(bean, "errorBean");
		
		smartValidator.validate(bean, errors);
		
		assertThat(errors.hasErrors()).isEqualTo(false);
	}
	
	
	@Configuration
	static class Config {
		
		@Bean
		public LocalValidatorFactoryBean LocalValidatorFactoryBean() {
			return new LocalValidatorFactoryBean();
		}
		
	}
	
	static class JavaBean {
		
		@NotNull
		@Max(5)
		private Integer number;

		@NotNull
		@Future
		private LocalDate date;

		public JavaBean(Integer number, LocalDate date) {
			this.number = number;
			this.date = date;
		}

		public Integer getNumber() {
			return number;
		}

		public LocalDate getDate() {
			return date;
		}
		
	}
	
}
