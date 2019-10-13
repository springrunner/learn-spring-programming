package showcase.mvc.form;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/form")
public class FormController {

	@ModelAttribute("inquiryTypes")
	public InquiryType[] createFormBean() {
		return InquiryType.values();
	}
	
	@GetMapping
	public ModelAndView form() {
		return new ModelAndView("mvc-form", "formBean", new FormBean());
	}

	@PostMapping
	public String processSubmit(@Valid FormBean formBean, BindingResult result, RedirectAttributes redirectAttrs) {
		// if have a request data errors, back to the form view.
		if (result.hasErrors()) {
			return "mvc-form";
		}

		String message = "Form submitted successfully.  Bound " + formBean;

		// store a success message for rendering on the next request after redirect
		// redirect back to the form to render the success message along with newly bound values
		redirectAttrs.addFlashAttribute("message", message);
		return "redirect:/form";
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String processAjaxSubmit(@RequestBody @Valid AjaxFormBean formBean) {
		return "Form submitted successfully.  Bound " + formBean;
	}
	
}
