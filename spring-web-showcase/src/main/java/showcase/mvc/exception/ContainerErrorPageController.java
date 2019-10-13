package showcase.mvc.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/exceptions/container-error-page")
public class ContainerErrorPageController {

    // See showcase.mvc.exception.ExceptionHandlingConfiguration#registerErrorPages

    @GetMapping("/throw")
    public void errorThrow() {
        throw new ContainerErrorPageException();
    }

    @GetMapping("/handle")
    public String handleContainerErrorPageException(WebRequest webRequest) {
        Exception exception = (Exception) webRequest.getAttribute("javax.servlet.error.exception", RequestAttributes.SCOPE_REQUEST);
        return String.format("%s handled!", exception);
    }

}
