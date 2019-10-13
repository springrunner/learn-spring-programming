package showcase.mvc.response;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/views/*")
public class RenderingViewsController {
	
	@GetMapping("simple")
	public String simple(Model model) {
		model.addAttribute("foo", "bar");
		model.addAttribute("fruit", "apple");
		return "views/simple";
	}
	
	@GetMapping("beanName")
	public String usingBeanNameViewResolver(Model model) {
		model.addAttribute("javaBean", JavaBean.of("bar", "mango"));
		return "delegateDataBindingView";
	}

	@GetMapping("viewName")
	public void usingRequestToViewNameTranslator(Model model) {
		model.addAttribute("foo", "bar");
		model.addAttribute("fruit", "mango");
	}

	@GetMapping("path-variables/{foo}/{fruit}")
	public String pathVars(@PathVariable String foo, @PathVariable String fruit) {
		// No need to add @PathVariables "foo" and "fruit" to the model
		// They will be merged in the model before rendering
		return "views/simple";
	}

	@GetMapping("data-binding/{foo}/{fruit}")
	public String dataBinding(@Valid JavaBean javaBean, Model model) {
		// JavaBean "foo" and "fruit" properties populated from URI variables 
		return "views/dataBinding";
	}
	
	@GetMapping("text")
	public ModelAndView text() {
		ModelAndView mv = new ModelAndView();		
		mv.addObject("foo", "bar");
		mv.addObject("fruit", "apple");
		mv.setViewName("text");
		
		return mv;
	}	

}