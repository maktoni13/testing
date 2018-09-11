<%@include file="jspf/header.jspf" %>
<%@include file="jspf/navigation.jspf" %>

<div class="container">
    <form class="text-center" method="post" action="${pageContext.request.contextPath}/testing/api/login">
        <div class="form-group">
            <c:if test="${lang == 'en'}">
                <h1 class="h3 mb-3 font-weight-normal">${sessionScope.testPass.name}
                    / ${sessionScope.testPass.theme.name}</h1>
            </c:if>
            <c:if test="${lang == 'ua'}">
                <h1 class="h3 mb-3 font-weight-normal">${sessionScope.testPass.nameUA}
                    / ${sessionScope.testPass.theme.nameUA}</h1>
            </c:if>
        </div>
        <c:if test="${lang == 'en'}">
            <p>${sessionScope.testPass.description}</p>
        </c:if>
        <c:if test="${lang == 'ua'}">
            <p>${sessionScope.testPass.descriptionUA}</p>
        </c:if>
    </form>
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
                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                            <input type="checkbox" class="custom-control-input" id="${answer.id}" value="${answer.id}">
                            <label class="custom-control-label" for="${answer.id}">${answer.description}</label>
                        </div>
                    </c:if>
                    <c:if test="${lang == 'ua'}">
                        <div class="custom-control custom-checkbox my-1 mr-sm-2">
                            <input type="checkbox" class="custom-control-input" id="${answer.id}" value="${answer.id}">
                            <label class="custom-control-label" for="${answer.id}">${answer.descriptionUA}</label>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </form>
    </div>
</div>
