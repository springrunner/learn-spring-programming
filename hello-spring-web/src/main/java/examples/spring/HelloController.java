package examples.spring;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	@RequestMapping("/hello")
	public void hello(@RequestParam("name") String name, Model models) {
		// Model 생성
		HelloModel model = new HelloModel(name);
		models.addAttribute("hello", model);
		
		// ViewName 지정
		// String viewName = "/WEB-INF/templates/HelloView.jsp";
		
		// ModelAndView mav = new ModelAndView();
		// mav.setViewName(viewName);
	}

	public static class HelloModel {
		
		private String name;
		
		@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
		private Date currentDatetime;
		
		public HelloModel(String name) {
			if (!StringUtils.hasText(name)) {
				this.name = "스프링 웹 애플리케이션";
			} else {
				this.name = name;				
			}
			this.currentDatetime = new Date();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Date getCurrentDatetime() {
			return currentDatetime;
		}

		public void setCurrentDatetime(Date currentDatetime) {
			this.currentDatetime = currentDatetime;
		}
		
	}
	
}
