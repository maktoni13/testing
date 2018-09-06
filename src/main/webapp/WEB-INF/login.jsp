<%@include file="jspf/header.jspf"%>
<%@include file="jspf/navigation.jspf"%>
<h1><fmt:message key="label.login"/></h1><br/>

<label>${loginErrorMessage}</label>

<form method="post" action="${pageContext.request.contextPath}/testing/api/login">
    <input type="text" name="username" value="${username}"><br/>
    <input type="password" name="password"><br/><br/>
    <input class="button" type="submit" value="OK">
</form>
<br/>

<%@include file="jspf/footer.jspf"%>