<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>Hello, Spring Web Application</title>    
  <style type="text/css">
    html, body {height:100%;}
    html {display:table; width:100%;}
    body {display:table-cell; text-align:center; vertical-align:middle;}
  </style>
</head>
<body>
  <h2>${hello.name}님 안녕하세요.</h2>
  <p>현재 시간은 <spring:eval expression="hello.currentDatetime"/>입니다.</p>
</body>
</html>