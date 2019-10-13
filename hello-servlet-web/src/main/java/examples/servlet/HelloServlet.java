package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/hello" })
public class HelloServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String requestName = request.getParameter("name");
		String servletName = getServletConfig().getServletName();

		StringBuilder html = new StringBuilder();
		html.append("<html>");
		html.append("  <head>");
		html.append("    <title>초 간단 자바 서블릿 개발하기</title>");
		html.append("  </head>");
		html.append("  <body>");
		html.append("    <h1>" + requestName + "님 안녕하세요.</h1>");
		html.append("    <h1>저는 " + servletName + "입니다.</h1>");
		html.append("  </body>");
		html.append("</html>");

		response.setStatus(200);
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter writer = response.getWriter();
		writer.write(html.toString());
	}

}