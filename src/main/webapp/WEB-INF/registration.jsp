<%--
  Created by IntelliJ IDEA.
  User: MakuhinAnton
  Date: 28.08.2018
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@include file="jspf/header.jspf"%>
<%@include file="jspf/navigation.jspf"%>

<h1><fmt:message key="label.registration.page.title"/></h1><br/>
<div class="container">
    <form action="registration" method="post">

        <label>${registrationErrorMessage}</label>
        <br/>

        <input type="text" name="username" id="user-name-label" value="${username}">
        <label for="user-name-label"><fmt:message key="label.registration.username.label" /></label>
        <br/>

        <input type="password" name="password" id="password-label">
        <label for="password-label"><fmt:message key="label.registration.password.label" /></label>
        <br/>

        <input type="password" name="confirm-password" id="confirm-password-label">
        <label for="confirm-password-label"><fmt:message key="label.registration.confirm.password.label" /></label>
        <br/>

        <input type="text" name="email" id="email-label" value="${email}">
        <label for="email-label"><fmt:message key="label.registration.email.label" /></label>
        <br/>

        <input type="text" name="confirm-email" id="confirm-email-label" value="${confirmEmail}">
        <label for="confirm-email-label"><fmt:message key="label.registration.confirm.email.label" /></label>
        <br/>

        <input type="text" name="first-name" id="first-name-label" value="${firstName}">
        <label for="first-name-label"><fmt:message key="label.registration.first.name.label" /></label>
        <br/>

        <input type="text" name="first-name-ua" id="first-name-ua-label" value="${firstNameUA}">
        <label for="first-name-ua-label"><fmt:message key="label.registration.first.name.ua.label" /></label>
        <br/>

        <input type="text" name="last-name" id="last-name-label" value="${lastName}">
        <label for="last-name-label"><fmt:message key="label.registration.last.name.label" /></label>
        <br/>

        <input type="text" name="last-name-ua" id="last-name-ua-label" value="${lastNameUA}">
        <label for="last-name-ua-label"><fmt:message key="label.registration.last.name.ua.label" /></label>
        <br/>

        <button type="submit" value="registration"><fmt:message key="label.registration.button"/></button>

    </form>
</div>

<%@include file="jspf/footer.jspf"%>