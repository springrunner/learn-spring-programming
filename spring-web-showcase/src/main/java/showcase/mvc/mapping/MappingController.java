package showcase.mvc.mapping;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlRootElement;

@RestController
public class MappingController {

	@RequestMapping(value="/mapping/path")
	public String byPath() {
		return "Mapped by path!";
	}

	@RequestMapping({"/mapping/path/first", "/mapping/path/second"})
	public String byPaths(HttpServletRequest request) {
		return "Mapped by multiple path ('" + request.getRequestURI() + "')";
	}
		
	@RequestMapping(value="/mapping/path/*")
	public String byPathPattern(HttpServletRequest request) {
		return "Mapped by path pattern ('" + request.getRequestURI() + "')";
	}

	
	@RequestMapping(value="/mapping/method", method=RequestMethod.GET)
	public String byGetMethod() {
		return "Mapped by path + get method";
	}
	
	@RequestMapping(value="/mapping/method", method=RequestMethod.POST)
	public String byPostMethod() {
		return "Mapped by path + post method";
	}
	
	@RequestMapping(value="/mapping/methods", method={RequestMethod.GET, RequestMethod.POST})
	public String byMethods(HttpMethod method) {
		return "Mapped by path + multiple method ('" + method + "')";
	}		

	
	@RequestMapping(value="/mapping/parameter", method=RequestMethod.GET, params="data")
	public String byParameter() {
		return "Mapped by path + method + presence of query parameter!";
	}

	@RequestMapping(value="/mapping/parameter", method=RequestMethod.GET, params="!data")
	public String byParameterNegation() {
		return "Mapped by path + method + not presence of query parameter!";
	}
	
	
	@RequestMapping(value="/mapping/header", method=RequestMethod.GET, headers="CustomHeader=mvc")
	public String byHeader() {
		return "Mapped by path + method + presence of header!";
	}

	@RequestMapping(value="/mapping/header", method=RequestMethod.GET, headers="!CustomHeader")
	public String byHeaderNegation() {
		return "Mapped by path + method + absence of header!";
	}	
	

	@RequestMapping(value="/mapping/produces", produces="application/json")
	public JavaBean byProducesJson() {
		return new JavaBean("JSON");
	}

	@RequestMapping(value="/mapping/produces", produces="application/xml")
	public JavaBean byProducesXml() {
		return new JavaBean("XML");
	}


    @RequestMapping(value="/mapping/consumes", consumes="application/json")
    public String byConsumesJson(@RequestBody JavaBean javaBean) {
        return "Mapped by path + consumable media type (javaBean '" + javaBean + "')";
    }

    @RequestMapping(value="/mapping/consumes", consumes="application/xml")
    public String byConsumesXml(@RequestBody JavaBean javaBean) {
        return "Mapped by path + consumable media type (javaBean '" + javaBean + "')";
    }


    @RequestMapping("/mapping/template/{value}")
    public String byTemplate(@PathVariable("value") String value) {
        return "Mapped by path + uri template (/mapping/template/{value}, value = '" + value + "')";
    }

    @RequestMapping("/mapping/template/{value}/path")
    public String byTemplateAndPath(@PathVariable("value") String value) {
        return "Mapped by path + uri template (/mapping/template/{value}/path, value = '" + value + "')";
    }


	@XmlRootElement
	public static class JavaBean {
		
		private String name;

		public JavaBean() {
			
		}
		
		public JavaBean(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("JavaBean{");
			sb.append("name='").append(name).append('\'');
			sb.append('}');
			return sb.toString();
		}

	}
	
}