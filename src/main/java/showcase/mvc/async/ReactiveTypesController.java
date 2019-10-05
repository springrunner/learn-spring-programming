package showcase.mvc.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/async/reactive/")
public class ReactiveTypesController {

    @GetMapping("/mono/response-body")
    public @ResponseBody Mono<String> monoResponseBody() {
        return Mono.just("Mono")
                   .delayElement(Duration.ofSeconds(2));
    }

    @GetMapping("/mono/model-and-view")
    public Mono<ModelAndView> monoModelAndView() {
        return Mono.just(new ModelAndView("async/reactive", "javaBean", new JavaBean("bar", "apple")))
                   .delayElement(Duration.ofSeconds(2));
    }

    @GetMapping(value = "/flux/response-body", produces = "application/octet-stream")
    public @ResponseBody Flux<String> fluxResponseBody() {
        return Flux.fromStream(IntStream.rangeClosed(1, 5).mapToObj(number -> String.format("Count publish: %d\n", number)))
                   .delayElements(Duration.ofSeconds(2));
    }

}
