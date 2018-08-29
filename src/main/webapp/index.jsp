<%@include file="WEB-INF/jspf/header.jspf"%>

<br/>
<a href="${pageContext.request.contextPath}/login.jsp"><fmt:message key="message.login"/></a>
<br>
<a href="${pageContext.request.contextPath}/registration.jsp"><fmt:message key="message.registration.page.link"/></a>
<br>
<a href="${pageContext.request.contextPath}/exception"><fmt:message key="message.exception"/></a>
<br>

<%@include file="WEB-INF/jspf/footer.jspf"%>