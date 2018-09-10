<%@include file="../jspf/header.jspf" %>
<%@include file="../jspf/navigation.jspf" %>
<form class="align-middle" method="post" action="${pageContext.request.contextPath}/testing/api/admin/processtheme">
    <div class="container">
        <div class="form-group">
            <div class="col-md-6 mb-3">
                <c:if test="${not empty theme.id && theme.id > 0}">
                    <h1 class="h3 mb-3 font-weight-normal"><fmt:message
                            key="label.theme.page.please.edit.theme.page"/><c:if
                            test="${lang == 'en'}">${theme.name}</c:if><c:if
                            test="${lang == 'ua'}">${theme.nameUA}</c:if></h1>
                </c:if>
                <c:if test="${empty theme.id || theme.id == 0}">
                    <h1 class="h3 mb-3 font-weight-normal"><fmt:message
                            key="label.theme.page.please.add.theme"/><c:if
                            test="${lang == 'en'}">${theme.name}</c:if><c:if
                            test="${lang == 'ua'}">${theme.nameUA}</c:if></h1>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-6 mb-3">
                <label><fmt:message key="label.theme.page.theme.name" var="themeNameLabel"/></label>
                <label for="inputThemeName"><fmt:message key="label.theme.page.theme.name"/>:</label>
                <input type="text" class="form-control" id="inputThemeName" name="themeName"
                       placeholder="${themeNameLabel}"
                       value="${theme.name}" required>
            </div>
            <div class="col-md-6 mb-3">
                <label><fmt:message key="label.theme.page.theme.name.ua" var="themeNameUALabel"/></label>
                <label for="inputThemeNameUA">${themeNameUALabel}:</label>
                <input type="text" class="form-control" id="inputThemeNameUA" name="themeNameUA"
                       placeholder="${themeNameUALabel}"
                       value="${theme.nameUA}"
                       required>
            </div>
            <div class="col-md-12 mb-3">
                <label class="sr-only"><fmt:message key="label.theme.page.theme.description"
                                                    var="themeDescriptionLabel"/></label>
                <label for="inputDescription">${themeDescriptionLabel}:</label>
                <textarea class="form-control" id="inputDescription" name="themeDescription"
                          placeholder="${themeDescriptionLabel}"
                          required>${theme.description}</textarea>
            </div>
            <div class="col-md-12 mb-3">
                <label class="sr-only"><fmt:message key="label.theme.page.theme.description.ua"
                                                    var="themeDescriptionUALabel"/></label>
                <label for="inputDescriptionUA">${themeDescriptionUALabel}:</label>
                <textarea class="form-control" id="inputDescriptionUA" name="themeDescriptionUA"
                          placeholder="${themeDescriptionUALabel}"
                          required>${theme.descriptionUA}</textarea>
            </div>
            <div class="col-md-12 mb-3">
                <button class="btn btn-sm btn-dark" type="submit"><fmt:message
                        key="label.theme.page.button.save"/></button>
                <a href="${pageContext.request.contextPath}/testing/api/common/themes"><fmt:message
                        key="label.theme.page.button.cancel"/></a>
            </div>
            <div class="col-md-12 mb-3">
                <label><span class="label alert-danger">${savingErrorMessage}</span></label>
            </div>

        </div>
    </div>
</form>
<%@include file="../jspf/footer.jspf" %>