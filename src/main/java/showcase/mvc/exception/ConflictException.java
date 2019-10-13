package showcase.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "the request could not be processed because of conflict in the current state of the resource.")
public class ConflictException extends RuntimeException {

}
