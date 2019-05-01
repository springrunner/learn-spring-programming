package showcase.mvc.data;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/data")
public class RequestDataController {

    @GetMapping("init")
    public void init(HttpServletResponse response) {
        CookieGenerator generator = new CookieGenerator();
        generator.setCookieName("foo");
        generator.addCookie(response, "bar");
    }

    @GetMapping("param")
    public String withParam(@RequestParam String foo) {
        return "Obtained 'foo' query parameter value '" + foo + "'";
    }

    @GetMapping("group")
    public String withParamGroup(JavaBean bean) {
        return "Obtained parameter group " + bean;
    }

    @GetMapping("path/{var}")
    public String withPathVariable(@PathVariable String var) {
        return "Obtained 'path/{var}' path variable value '" + var + "'";
    }

    @GetMapping("{path}/simple")
    public String withMatrixVariable(@PathVariable String path, @MatrixVariable String foo) {
        return "Obtained matrix variable 'foo=" + foo + "' from path segment '" + path + "'";
    }

    @GetMapping("{path1}/{path2}")
    public String withMatrixVariablesMultiple (
            @PathVariable String path1, @MatrixVariable(name="foo", pathVar="path1") String foo1,
            @PathVariable String path2, @MatrixVariable(name="foo", pathVar="path2") String foo2) {
        return "Obtained matrix variable foo=" + foo1 + " from path segment '" + path1
                + "' and variable 'foo=" + foo2 + " from path segment '" + path2 + "'";
    }

    @GetMapping("header")
    public String withHeader(@RequestHeader String Accept) {
        return "Obtained 'Accept' header '" + Accept + "'";
    }

    @GetMapping("cookie")
    public String withCookie(@CookieValue String foo) {
        return "Obtained 'foo' cookie '" + foo + "'";
    }

    @PostMapping("body")
    public String withBody(@RequestBody String body) {
        return "Posted request body '" + body + "'";
    }

    @PostMapping("entity")
    public String withEntity(HttpEntity<String> entity) {
        return "Posted request body '" + entity.getBody() + "'; headers = " + entity.getHeaders();
    }

}