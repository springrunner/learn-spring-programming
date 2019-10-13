package showcase.mvc.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Part;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

	@GetMapping
	public ModelAndView fileUploadForm() {
		return new ModelAndView("mvc-fileupload");
	}

	@PostMapping
	public ModelAndView processMultipartFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded MultipartFile[" + file.getOriginalFilename() + "]");

		return new ModelAndView("redirect:/fileupload");
	}

	@PostMapping(params = { "part=use" })
	public ModelAndView processPartUpload(Part file, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded Part[" + file.getSubmittedFileName() + "]");

		return new ModelAndView("redirect:/fileupload");
	}
	
}
