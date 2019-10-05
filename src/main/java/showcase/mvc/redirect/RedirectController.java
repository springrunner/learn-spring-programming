package showcase.mvc.redirect;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static showcase.mvc.redirect.RedirectController.Accounts.anyone;

@Controller
@RequestMapping("/redirect")
public class RedirectController {
	
	private final ConversionService conversionService;

	public RedirectController(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	@GetMapping("/uriTemplate")
	public String uriTemplate(RedirectAttributes redirectAttrs) {
		redirectAttrs.addAttribute("account", anyone());  // Used as URI template variable
		redirectAttrs.addAttribute("date", LocalDate.of(2011, 12, 31));  // Appended as a query parameter
		return "redirect:/redirect/{account}";
	}

	@GetMapping("/uriComponentsBuilder")
	public View uriComponentsBuilder() {
		String date = this.conversionService.convert(LocalDate.of(2011, 12, 31), String.class);
		String redirectUri = UriComponentsBuilder.fromPath("/redirect/{account}")
												 .queryParam("date", date)
												 .build()
											     .expand(anyone())
												 .encode()
												 .toUriString();
		return new RedirectView(redirectUri);
	}

	@GetMapping("/{account}")
	public String show(@PathVariable String account, @RequestParam(required=false) LocalDate date) {
		return "redirect/redirectResults";
	}


	static class Accounts {

		final static List<String> usernames = Arrays.asList(
				"dpunch0", "ctremlett1", "hreucastle2", "gbastistini3", "aphlipon4", "emaclure5",
				"aquilleash6", "rextall7", "hkropach8", "kwoolway9", "ghallea", "ahaccletonb",
				"ddoumercc", "ddowsd", "rgostagee", "bblunderfieldf", "eshouttg", "ppaviah",
				"fminkeri", "pwillsonj");

		static String anyone() {
			return usernames.get(new Random().nextInt(20));
		}

	}
}
