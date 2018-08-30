<%@include file="WEB-INF/jspf/header.jspf"%>
<%@include file="WEB-INF/jspf/navigation.jspf"%>

<div class="container" >
    <table class="table">
        <thead>
            <tr>
                <th scope="col"><fmt:message key="message.column.description.label" /></th>
                <th scope="col"><fmt:message key="message.column.description.ua.label" /></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${themeList}" var="theme">
                <tr>
                    <td>${theme.description}</td>
                    <td>${theme.descriptionUA}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="WEB-INF/jspf/footer.jspf"%>
