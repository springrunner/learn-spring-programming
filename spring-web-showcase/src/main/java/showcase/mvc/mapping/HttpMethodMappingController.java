package showcase.mvc.mapping;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/method-mapping")
public class HttpMethodMappingController {

	@GetMapping
	public String get() {
		return "Mapped by type, get, path + get method";
	}
	
	@PostMapping
	public String post() {
		return "Mapped by type, post, path + post method";
	}
	
	@PutMapping
	public String put() {
		return "Mapped by type, put, path + put method";
	}
	
	@PatchMapping
	public String patch() {
		return "Mapped by type, patch, path + patch method";
	}
	
	@DeleteMapping
	public String delete() {
		return "Mapped by type, delete, path + delete method";
	}
	
}
