package examples.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    final var requestName = request.getParameter("name");
    final var servletName = getServletConfig().getServletName();
    
    response.setStatus(200);
    response.setContentType("text/html");
    response.getWriter().write("""
          <html>
            <head>
              <title>초 간단 자바 서블릿 개발하기</title>
            </head>
            <body>
              <h1>%s님 안녕하세요.</h1>
              <h1>저는 %s입니다.</h1>
            </body>
          </html>
        """.formatted(requestName, servletName));
  }

}
