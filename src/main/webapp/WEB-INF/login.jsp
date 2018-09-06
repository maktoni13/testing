<%@include file="jspf/header.jspf"%>
<%@include file="jspf/navigation.jspf"%>
<!--<form class="form-signin" method="post" action="${pageContext.request.contextPath}/testing/api/login">-->
    <!--<img class="mb-4" src="../../assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">-->
<h2><br/></h2>
<form class="text-center" method="post" action="${pageContext.request.contextPath}/testing/api/login">
    <div class="form-group">
        <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="label.login.page.please.sign.in"/></h1>
    </div>
    <div class="form-group">
        <label for="inputUsername" class="sr-only"><fmt:message key="label.login.page.username" var="usernameLabel"/></label>
        <input type="text" id="inputUsername" placeholder="${usernameLabel}" name="username" value="${username}" required autofocus>
    </div>
    <div class="form-group">
        <label for="inputPassword" class="sr-only"><fmt:message key="label.login.page.password" var="passwordLabel"/></label>
        <input type="password" id="inputPassword" placeholder="${passwordLabel}" name="password" required>
    </div>
    <div class="form-group">
        <button class="btn btn-sm btn-dark" type="submit"><fmt:message key="label.login.page.sign.in.button"/></button>
    </div>
    <div class="form-group">
        <p class="mt-5 mb-3 text-muted">&copy; 2018</p>
    </div>
    <div class="form-group">
        <span class="label alert-danger">${loginErrorMessage}</span>
    </div>
</form>

<%@include file="jspf/footer.jspf"%>