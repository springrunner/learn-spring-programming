package showcase.mvc.convert;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.util.NumberUtils;

public class TypeConversionTest {
	
	@Test
	public void conversionService() {
		FormattingConversionService conversionService = new DefaultFormattingConversionService();
		conversionService.addConverter(String.class, Integer.class, new StringToNumberConverter<>(Integer.class));
		conversionService.addFormatter(new StringToBooleanFormatter());
		
		assertThat(conversionService.convert("2019", Integer.class)).isEqualTo(2019);
		assertThat(conversionService.convert("true", Boolean.class)).isEqualTo(true);
		assertThat(conversionService.convert(true, String.class)).isEqualTo("True");
	}
	
	@Test
	public void typeConverter() {
		SimpleTypeConverter typeConverter = new SimpleTypeConverter();
		typeConverter.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
		typeConverter.registerCustomEditor(Boolean.class, new CustomBooleanEditor(true));
		
		assertThat(typeConverter.convertIfNecessary("2019", Integer.class)).isEqualTo(2019);
		assertThat(typeConverter.convertIfNecessary("true", Boolean.class)).isEqualTo(true);
	}

	
	static class StringToNumberConverter<T extends Number> implements Converter<String, T> {
		
		private final Class<T> targetType;
		
		public StringToNumberConverter(Class<T> targetType) {
			this.targetType = targetType;
		}

		@Override
		public T convert(String source) {
			if (source.isEmpty()) {
		      return null;
		    }
		    return NumberUtils.parseNumber(source, this.targetType);
		}

	}
	
	static class StringToBooleanFormatter implements Formatter<Boolean> {

		@Override
		public String print(Boolean object, Locale locale) {
			return Boolean.TRUE == object ? "True" : "False";
		}

		@Override
		public Boolean parse(String text, Locale locale) throws ParseException {
			String value = text.trim();
			if (value.isEmpty()) {
				return null;
			} else {
				value = value.toLowerCase();
			}
			
			if (Objects.equals("true", value)) {
				return Boolean.TRUE;
			}
			else if (Objects.equals("false", value)) {
				return Boolean.FALSE;
			} else {
				throw new IllegalArgumentException("Invalid boolean value '" + text + "'");
			}
		}

	}
	
}
