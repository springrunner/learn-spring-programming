package examples.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/improved-hello"})
public class ImprovedHelloServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    final var requestName = request.getParameter("name");
    final var servletName = getServletConfig().getServletName();

    request.setAttribute("requestName", requestName);
    request.setAttribute("servletName", servletName);

    final var requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/hello.jsp");
    requestDispatcher.forward(request, response);
  }

}
