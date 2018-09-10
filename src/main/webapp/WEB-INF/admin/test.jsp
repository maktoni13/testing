<%@include file="../jspf/header.jspf" %>
<%@include file="../jspf/navigation.jspf" %>
<form class="align-middle" method="post"
      action="${pageContext.request.contextPath}/testing/api/admin/processtest?themeid=${test.theme.id}&testid=${test.id}&currentquestion=${question.idLocal}">
    <div class="container">
        <div class="form-group">
            <div class="col-md-6 mb-3">
                <c:if test="${not empty test.id && test.id > 0}">
                    <h1 class="h3 mb-3 font-weight-normal"><fmt:message
                            key="label.test.page.please.edit.test.page"/> <c:if
                            test="${lang == 'en'}">${test.name}</c:if><c:if
                            test="${lang == 'ua'}">${test.nameUA}</c:if></h1>
                </c:if>
                <c:if test="${empty test.id || test.id == 0}">
                    <h1 class="h3 mb-3 font-weight-normal"><fmt:message
                            key="label.test.page.please.add.test"/> <c:if
                            test="${lang == 'en'}">${test.name}</c:if><c:if
                            test="${lang == 'ua'}">${test.nameUA}</c:if></h1>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-12 mb-3">
                <label><span class="label alert-danger">${savingErrorMessage}</span></label>
            </div>
            <div class="col-md-12 mb-3">
                <label for="saveTest" class="sr-only"><fmt:message key="label.test.page.button.save"/></label>
                <input name="save" class="btn btn-sm btn-dark" type="submit" value="Save test" id="saveTest">
                <label for="addQuestion" class="sr-only"><fmt:message
                        key="label.test.page.button.add.question"/></label>
                <input name="save" class="btn btn-sm btn-dark" type="submit" value="Add question" id="addQuestion">
                <label for="saveQuestion" class="sr-only"><fmt:message
                        key="label.test.page.button.save.question"/></label>
                <input name="save" class="btn btn-sm btn-dark" type="submit" value="Save question" id="saveQuestion">
                <a href="${pageContext.request.contextPath}/testing/api/admin/processtest?action=cancel&themeid=${test.theme.id}"><fmt:message
                        key="label.test.page.button.cancel"/></a>
                <c:if test="${test.inactive}">
                    <label><input type="checkbox" name="inactive" value="on" checked="checked"><fmt:message
                            key="label.test.page.test.inactive"/></label>
                </c:if>
                <c:if test="${not test.inactive}">
                    <label><input type="checkbox" name="inactive" value="on"><fmt:message
                            key="label.test.page.test.inactive"/></label>
                </c:if>
            </div>
            <div class="col-md-12 mb-3">
                <table class="table table-bordered table-sm">
                    <thead></thead>
                    <tbody>
                    <tr>
                        <td>
                            <label><fmt:message key="label.test.page.test.name.ua" var="testNameLabel"/></label>
                            <label for="inputTestName"><fmt:message key="label.test.page.test.name"/>:</label>
                            <input type="text" class="form-control" id="inputTestName" name="testName"
                                   placeholder="${testNameLabel}"
                                   value="${test.name}" required>
                        </td>
                        <td>
                            <label><fmt:message key="label.test.page.test.name.ua" var="testNameUALabel"/></label>
                            <label for="inputTestNameUA">${testNameUALabel}:</label>
                            <input type="text" class="form-control" id="inputTestNameUA" name="testNameUA"
                                   placeholder="${testNameUALabel}"
                                   value="${test.nameUA}"
                                   required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label class="sr-only"><fmt:message key="label.test.page.test.description"
                                                                var="testDescriptionLabel"/></label>
                            <label for="inputDescription">${testDescriptionLabel}:</label>
                            <textarea class="form-control" id="inputDescription" name="testDescription"
                                      placeholder="${testDescriptionLabel}"
                                      required>${test.description}</textarea>
                        </td>
                        <td>
                            <label class="sr-only"><fmt:message key="label.test.page.test.description.ua"
                                                                var="testDescriptionUALabel"/></label>
                            <label for="inputDescriptionUA">${testDescriptionUALabel}:</label>
                            <textarea class="form-control" id="inputDescriptionUA" name="testDescriptionUA"
                                      placeholder="${testDescriptionUALabel}"
                                      required>${test.descriptionUA}</textarea>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <hr class="mb-4">
                <nav aria-label="question navigation">
                    <ul class="pagination justify-content-center">
                        <c:forEach items="${test.questions}" var="quest">
                            <c:if test="${not empty question && question.idLocal == quest.idLocal}">
                                <li class="page-item active">
                            </c:if>
                            <c:if test="${empty question || (not empty question && question.idLocal != quest.idLocal)}">
                                <li class="page-item">
                            </c:if>
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/testing/api/admin/processtest?themeid=${test.theme.id}&testid=${test.id}&currentquestion=${quest.idLocal}">${quest.idLocal}</a>
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
                                        <c:if test="${answer.correct}">
                                            <input type="checkbox" name="answer" value="${answer.idLocal}"
                                                   aria-label="Checkbox for following text input"
                                                   checked="checked">
                                        </c:if>
                                        <c:if test="${not answer.correct}">
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
<%@include file="../jspf/footer.jspf" %>