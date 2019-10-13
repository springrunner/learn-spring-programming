package showcase.mvc.mapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type-mapping/**")
public class TypeMappingController {

	@RequestMapping("/path")
	public String byPath() {
		return "Mapped by type path + path!";
	}

	@RequestMapping(value="/method", method=RequestMethod.GET)
	public String byMethod() {
		return "Mapped by type path + path + method";
	}	
	
}
