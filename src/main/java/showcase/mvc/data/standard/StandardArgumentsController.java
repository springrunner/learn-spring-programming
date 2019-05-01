package showcase.mvc.data.standard;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.Principal;
import java.util.Locale;

@RestController
public class StandardArgumentsController {

    // request related

    @GetMapping("/data/standard/request")
    public String standard(HttpServletRequest request, Principal principal, Locale locale) {
        return "request = " + request + ", " + "principal = " + principal + ", " + "locale = " + locale;
    }

    @PostMapping("/data/standard/request/reader")
    public String standard(Reader requestBodyReader) throws IOException {
        return "Read char request body = " + FileCopyUtils.copyToString(requestBodyReader);
    }

    @PostMapping("/data/standard/request/is")
    public String standard(InputStream requestBodyIs) throws IOException {
        return "Read binary request body = " + new String(FileCopyUtils.copyToByteArray(requestBodyIs));
    }

    // response related

    @GetMapping("/data/standard/response")
    public String standard(HttpServletResponse response) {
        return "response = " + response;
    }

    @GetMapping("/data/standard/response/writer")
    public void standard(Writer responseWriter) throws IOException {
        responseWriter.write("Wrote char response using Writer");
    }

    @GetMapping("/data/standard/response/os")
    public void standard(OutputStream os) throws IOException {
        os.write("Wrote binary response using OutputStream".getBytes());
    }

    // HttpSession

    @GetMapping("/data/standard/session")
    public String standard(HttpSession session) {
        return "session = " + session;
    }

}