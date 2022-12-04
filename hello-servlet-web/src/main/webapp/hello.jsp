<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% 
  String requestName = request.getParameter("name");
  String servletName = config.getServletName();
%>
<html>
<head>
  <title>초 간단 JSP 개발하기</title>
</head>
<body>
  <h1><%= requestName %>님 안녕하세요.</h1>
  <h1>저는 <%= servletName %>입니다.</h1>
</body>
</html>
