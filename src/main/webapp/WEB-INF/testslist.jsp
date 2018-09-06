<%@include file="jspf/header.jspf" %>
<%@include file="jspf/navigation.jspf" %>

<div class="container">
    <!--<p>The .table-sm class is used to make the table smaller by cutting cell padding in half.</p>-->
    <h2><c:if test="${lang == 'en'}">${theme.name}</c:if><c:if test="${lang == 'ua'}">${theme.nameUA}</c:if><fmt:message
            key="label.testslist.body.title"/></h2>
    <table class="table table-bordered table-sm">

        <thead>
        <tr>
            <th scope="col"><fmt:message key="label.column.name.label"/></th>
            <th scope="col"><fmt:message key="label.column.description.label"/></th>
            <th scope="col"><fmt:message key="label.column.actions.label"/></th>
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
                       href="${pageContext.request.contextPath}/testing/api/common/passing?testid=${test.id}"><fmt:message
                            key="label.testslist.page.passing.the.test.link"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="jspf/footer.jspf" %>
