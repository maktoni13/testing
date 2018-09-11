<%@include file="jspf/header.jspf" %>
<%@include file="jspf/navigation.jspf" %>

<div class="container">
    <div class="col-md-6 mb-3">
        <h2><fmt:message key="label.themes.list.page.title"/><p class="lead"><fmt:message key="label.themes.list.page.description"/></p></h2>
        <c:if test="${not empty sessionScope.authority && sessionScope.authority.toString() == 'ADMIN'}">
            <a class="nav-link"
               href="${pageContext.request.contextPath}/testing/api/admin/processtheme"><fmt:message
                    key="label.themes.list.page.adding.new.theme.link"/></a>
        </c:if>
    </div>
    <table class="table table-bordered table-sm">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="label.column.name.label"/></th>
            <th scope="col"><fmt:message key="label.column.description.label"/></th>
            <th scope="col"><fmt:message key="label.themes.column.go.to.test.link"/></th>
            <th scope="col"><fmt:message key="label.themes.column.edit.link"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${themeList}" var="theme">
            <tr>
                <c:if test="${lang == 'en'}">
                    <td>${theme.name}</td>
                    <td>${theme.description}</td>
                </c:if>
                <c:if test="${lang == 'ua'}">
                    <td>${theme.nameUA}</td>
                    <td>${theme.descriptionUA}</td>
                </c:if>
                <td>
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/testing/api/common/tests?themeid=${theme.id}"><fmt:message
                            key="label.themes.go.to.test.link"/></a>
                </td>
                <td>
                    <c:if test="${not empty sessionScope.authority && sessionScope.authority.toString() == 'ADMIN'}">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/testing/api/admin/processtheme?themeid=${theme.id}"><fmt:message
                                key="label.themes.list.page.edit.theme.link"/></a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="jspf/footer.jspf" %>
