package showcase.mvc.convert;

import java.lang.annotation.*;

@Target(value={ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MaskFormat {
	String value();
}
