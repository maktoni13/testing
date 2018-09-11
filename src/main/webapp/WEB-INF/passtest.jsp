<%@include file="jspf/header.jspf" %>
<%@include file="jspf/navigation.jspf" %>
<form class="align-middle" method="post"
      action="${pageContext.request.contextPath}/testing/api/common/testsolve?summaryid=${summary.id}&questionid=${question.idLocal}">
    <div class="container">
        <div class="form-group">
            <div class="col-md-6 mb-3">
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-12 mb-3">
                <label><span class="label alert-danger">${savingErrorMessage}</span></label>
            </div>
            <div class="col-md-12 mb-3">
                <label for="saveTest" class="sr-only"><fmt:message key="label.test.page.button.save"/></label>
                <input name="save" class="btn btn-sm btn-dark" type="submit" value="Save test" id="saveTest">
                <label for="saveQuestion" class="sr-only"><fmt:message
                        key="label.test.page.button.save.question"/></label>
                <input name="save" class="btn btn-sm btn-dark" type="submit" value="Save question" id="saveQuestion">
                <a href="${pageContext.request.contextPath}/testing/api/admin/testsolve?action=cancel"><fmt:message
                        key="label.test.page.button.cancel"/></a>
            </div>
            <div class="col-md-12 mb-3">
                <table class="table table-bordered table-sm">
                    <thead></thead>
                    <tbody>
                    <tr>
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <hr class="mb-4">
                <nav aria-label="question navigation">
                    <ul class="pagination justify-content-center">
                        <c:forEach items="${summary.questions}" var="quest">
                            <c:if test="${not empty question && question.idLocal == quest.idLocal}">
                                <li class="page-item active">
                            </c:if>
                            <c:if test="${empty question || (not empty question && question.idLocal != quest.idLocal)}">
                                <li class="page-item">
                            </c:if>
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/testing/api/admin/testsolve?summaryid=${summary.id}&questionid=${quest.idLocal}">${quest.idLocal}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
                <table class="table table-bordered table-sm">
                    <thead></thead>
                    <tbody>
                    <tr>
                        <td>
                            <label><fmt:message key="label.test.page.question.description"
                                                var="questionDescriptionLabel"/></label>
                            <label for="inputQuestionDescription">${questionDescriptionLabel}:</label>
                            <input type="text" class="form-control" id="inputQuestionDescription"
                                   name="questionDescription"
                                   placeholder="${questionDescriptionLabel}"
                                   value="${question.description}" required>
                        </td>
                        <td>
                            <label><fmt:message key="label.test.page.question.description.ua"
                                                var="questionDescriptionUALabel"/></label>
                            <label for="inputQuestionDescriptionUA">${questionDescriptionUALabel}:</label>
                            <input type="text" class="form-control" id="inputQuestionDescriptionUA"
                                   name="questionDescriptionUA"
                                   placeholder="${questionDescriptionUALabel}"
                                   value="${question.descriptionUA}" required>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <hr class="mb-4">
                <form role="form">
                    <div class="checkbox">
                        <c:forEach items="${question.answers}" var="answer">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <c:if test="${answer.chosen}">
                                            <input type="checkbox" name="answer" value="${answer.idLocal}"
                                                   aria-label="Checkbox for following text input"
                                                   checked="checked">
                                        </c:if>
                                        <c:if test="${not answer.chosen}">
                                            <input type="checkbox" name="answer" value="${answer.idLocal}"
                                                   aria-label="Checkbox for following text input">
                                        </c:if>
                                    </div>
                                </div>
                                <input type="text" class="form-control" name="answerDesc${answer.idLocal}"
                                       value="${answer.description}" aria-label="Text input with checkbox">
                                <input type="text" class="form-control" name="answerDescUA${answer.idLocal}"
                                       value="${answer.descriptionUA}" aria-label="Text input with checkbox">
                            </div>
                        </c:forEach>
                    </div>
                </form>
            </div>
        </div>
    </div>
</form>
<%@include file="jspf/footer.jspf" %>