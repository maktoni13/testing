<%@include file="../jspf/header.jspf"%>
<%@include file="../jspf/navigation.jspf"%>

<div class="container" >
    <table class="table">
        <thead>
            <tr>
                <th scope="col"><fmt:message key="message.column.username.label" /></th>
                <th scope="col"><fmt:message key="message.column.first.name.label" /></th>
                <th scope="col"><fmt:message key="message.column.last.name.label" /></th>
                <th scope="col"><fmt:message key="message.column.first.name.ua.label" /></th>
                <th scope="col"><fmt:message key="message.column.last.name.ua.label" /></th>
                <th scope="col"><fmt:message key="message.column.enabled.label" /></th>
                <th scope="col"><fmt:message key="message.column.admin.label" /></th>
                <th scope="col"><fmt:message key="message.column.tests.completed.label" /></th>
                <th scope="col"><fmt:message key="message.column.average.evaluation.label" /></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${studentList}" var="student">
                <tr>
                    <td>${student.username}</td>
                    <td>${student.firstName}</td>
                    <td>${student.lastName}</td>
                    <td>${student.firstNameUA}</td>
                    <td>${student.lastNameUA}</td>
                    <td>${student.enabled}</td>
                    <td>${student.admin}</td>
                    <td>${student.testsCompleted}</td>
                    <td>${student.averageEvaluation}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="../jspf/footer.jspf"%>
