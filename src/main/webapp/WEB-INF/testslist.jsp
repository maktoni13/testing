<%@include file="jspf/header.jspf" %>
<%@include file="jspf/navigation.jspf" %>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="label.column.name.label"/></th>
            <th scope="col"><fmt:message key="label.column.description.label"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${testList}" var="test">
            <tr>
                <c:if test="${lang == 'en'}">
                    <td>${test.name}</td>
                    <td>${test.description}</td>
                    <td>${test.description}</td>
                </c:if>
                <c:if test="${lang == 'ua'}">
                    <td>${test.nameUA}</td>
                    <td>${test.descriptionUA}</td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="jspf/footer.jspf" %>
