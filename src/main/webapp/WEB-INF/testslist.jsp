<%@include file="jspf/header.jspf" %>
<%@include file="jspf/navigation.jspf" %>

<div class="container">
    <div class="col-md-6 mb-3">
        <h2><c:if test="${lang == 'en'}">${theme.name}</c:if><c:if
                test="${lang == 'ua'}">${theme.nameUA}</c:if><fmt:message
                key="label.tests.list.body.title"/><p class="lead"><fmt:message
                key="label.tests.list.page.description"/></p></h2>
        <c:if test="${not empty sessionScope.authority && sessionScope.authority.toString() == 'ADMIN'}">
            <a class="nav-link"
               href="${pageContext.request.contextPath}/testing/api/admin/processtest?themeid=${theme.id}&testid=0&currentquestion=1"><fmt:message
                    key="label.themes.list.page.adding.new.theme.link"/></a>
        </c:if>
    </div>
    <table class="table table-bordered table-sm">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="label.column.name.label"/></th>
            <th scope="col"><fmt:message key="label.column.description.label"/></th>
            <th scope="col"><fmt:message key="label.column.actions.label"/></th>
            <c:if test="${not empty sessionScope.authority && sessionScope.authority.toString() == 'ADMIN'}">
                <th scope="col"></th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${testList}" var="test">
            <tr>
                <c:if test="${lang == 'en'}">
                    <td>${test.name}</td>
                    <td>${test.description}</td>
                </c:if>
                <c:if test="${lang == 'ua'}">
                    <td>${test.nameUA}</td>
                    <td>${test.descriptionUA}</td>
                </c:if>
                <td>
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/testing/api/common/testpass?themeid=${theme.id}&testid=${test.id}"><fmt:message
                            key="label.tests.list.page.passing.the.test.link"/></a>
                </td>
                <c:if test="${not empty sessionScope.authority && sessionScope.authority.toString() == 'ADMIN'}">
                    <td>
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/testing/api/admin/processtest?themeid=${theme.id}&testid=${test.id}&currentquestion=1"><fmt:message
                                key="label.tests.list.page.edit.test.link"/></a>
                    </td>
                </c:if>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="jspf/footer.jspf" %>
