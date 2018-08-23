<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="message.admin.profile.title"/></title>
</head>
<body>

<h1><fmt:message key="message.admin.welcome.title"/></h1>
<a href="${pageContext.request.contextPath}/logout"><fmt:message key="message.logout"/></a>
</body>
</html>