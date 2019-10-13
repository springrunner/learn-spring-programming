package examples.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/improved-hello" })
public class ImprovedHelloServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String requestName = request.getParameter("name");
		String servletName = getServletConfig().getServletName();

		request.setAttribute("requestName", requestName);
		request.setAttribute("servletName", servletName);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/hello.jsp");
		requestDispatcher.forward(request, response);
	}

}