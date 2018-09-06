<%@include file="jspf/header.jspf" %>
<%@include file="jspf/navigation.jspf" %>

<div class="container">
    <h2>Small Table Example</h2>
    <p>The .table-sm class is used to make the table smaller by cutting cell padding in half.</p>
    <div class="container">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:forEach items="${sessionScope.testPass.questions}" var="quest">

                    <c:if test="${question.id == quest.id}">
                        <li class="page-item active">
                    </c:if>
                    <c:if test="${question.id != quest.id}">
                        <c:if test="${quest.answered}">
                            <li class="page-item disabled">
                        </c:if>
                        <c:if test="${!quest.answered}">
                            <li class="page-item">
                        </c:if>
                    </c:if>
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/testing/api/common/passing?testid=${test.id}&currentquestion=${quest.id}">${quest.idLocal}</a>
                    </li>

                </c:forEach>
            </ul>
        </nav>
    </div>
    <div class="container">
        <c:if test="${lang == 'en'}">
            <h2>${question.description}</h2>
        </c:if>
        <c:if test="${lang == 'ua'}">
            <h2>${question.descriptionUA}</h2>
        </c:if>
        <form role="form">
            <div class="checkbox">
                <c:forEach items="${question.answers}" var="answer">
                    <c:if test="${lang == 'en'}">
                        <label><input type="checkbox" value="${answer.id}">${answer.description}</label><br/>
                    </c:if>
                    <c:if test="${lang == 'ua'}">
                        <label><input type="checkbox" value="${answer.id}">${answer.descriptionUA}</label><br/>
                    </c:if>
                </c:forEach>
            </div>
        </form>
    </div>
</div>
