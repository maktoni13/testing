<%@include file="jspf/header.jspf" %>
<%@include file="jspf/navigation.jspf" %>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="label.themes.column.go.to.test.link"/></th>
            <th scope="col"><fmt:message key="label.column.name.label"/></th>
            <th scope="col"><fmt:message key="label.column.description.label"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${themeList}" var="theme">
            <tr>
                <td><a href="${pageContext.request.contextPath}/testing/api/common/tests?themeid=${theme.id}"
                       class="btn btn-link" role="button"><fmt:message key="label.themes.go.to.test.link"/></a></td>
                <c:if test="${lang == 'en'}">
                    <td>${theme.name}</td>
                    <td>${theme.description}</td>
                </c:if>
                <c:if test="${lang == 'ua'}">
                    <td>${theme.nameUA}</td>
                    <td>${theme.descriptionUA}</td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="jspf/footer.jspf" %>
