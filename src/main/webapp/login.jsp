<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><fmt:message key="message.login"/></title>
</head>
<body>
<h1><fmt:message key="message.login"/></h1><br/>
<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="text" name="username"><br/>
    <input type="password" name="password"><br/><br/>
    <input class="button" type="submit" value="login"><fmt:message key="message.login"/>
</form>
<br/>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="message.logout"/></a>
</body>
</html>