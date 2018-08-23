<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.*" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="message.title"/></title>
</head>
<body>
<h2><fmt:message key="message.login"/></h2>
<br/>
<a href="${pageContext.request.contextPath}/login.jsp"><fmt:message key="message.login"/></a>
<br>
<a href="${pageContext.request.contextPath}/exception"><fmt:message key="message.exception"/></a>
<br>
</body>
</html>
