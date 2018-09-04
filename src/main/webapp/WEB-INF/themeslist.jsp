<%@include file="jspf/header.jspf"%>
<%@include file="jspf/navigation.jspf"%>

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

<script src="../webjars/bootstrap/4.0.0/js/bootstrap.js"></script>
<%@include file="jspf/footer.jspf"%>
