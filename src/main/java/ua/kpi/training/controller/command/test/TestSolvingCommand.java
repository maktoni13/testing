package ua.kpi.training.controller.command.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.logger.LoggerMessages;
import ua.kpi.training.model.entity.Answer;
import ua.kpi.training.model.entity.Question;
import ua.kpi.training.model.entity.Summary;
import ua.kpi.training.model.service.SummaryTestSolveService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSolvingCommand implements Command {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(TestPassingCommand.class);

    private SummaryTestSolveService summaryTestSolveService;

    private static final String SUMMARY_ID = "summaryid";
    private static final String QUESTION_ID_PARAM = "questionid";
    private static final String ACTION_PARAM = "action";
    private static final String ACTION_CANCEL = "cancel";
    private static final String SUMMARY = "summary";
    private static final String QUESTION = "question";
    private static final String SUBMIT_PARAM = "save";
    private static final String SUBMIT_VALUE_SAVE_QUESTION = "Save question";
    private static final String SUBMIT_VALUE_SAVE = "Save summary";
    private static final String ANSWERS = "answer";
    private static final String ANSWER_DESC = "answerDesc";
    private static final String ANSWER_UA_DESC = "answerDescUA";
    private static final String ATTR_ERROR = "savingErrorMessage";

    public TestSolvingCommand(SummaryTestSolveService summaryTestSolveService) {
        this.summaryTestSolveService = summaryTestSolveService;
    }

    private void removeAttributes(HttpServletRequest request){
        HttpSession session = request.getSession();
        request.removeAttribute(SUMMARY);
        request.removeAttribute(QUESTION);
        session.removeAttribute(SUMMARY);
        session.removeAttribute(QUESTION);
//        Arrays.asList(SUMMARY, QUESTION);
    }

    private void saveQuestionData(HttpServletRequest request, Summary summary, Question question){
        List<String> chosenAnswers;
        String[] correct = request.getParameterValues(ANSWERS);
        if (correct == null) {
            chosenAnswers = new ArrayList<>();
        } else {
            chosenAnswers = Arrays.asList(correct);
        }

        for (Answer answer : question.getAnswers()) {
            answer.setDescription(request.getParameter(
                    ANSWER_DESC + answer.getIdLocal()));
            answer.setDescriptionUA(request.getParameter(
                    ANSWER_UA_DESC + answer.getIdLocal()));
            answer.setChosen(chosenAnswers.contains(Integer.toString(answer.getIdLocal())));
        }
    }

    private void saveSummaryData(HttpServletRequest request, Summary summary, Question question) {
        HttpSession session = request.getSession();
        request.setAttribute(SUMMARY, summary);
        session.setAttribute(SUMMARY, summary);
        request.setAttribute(QUESTION, question);
        session.setAttribute(QUESTION, question);
        request.setAttribute(ATTR_ERROR, summary.getValidationResult());
        summary.setValidationResult(new StringBuilder());
    }


    @Override
    public String execute(HttpServletRequest request) {

        String summaryIdText = request.getParameter(SUMMARY_ID);
        String questionIdText = request.getParameter(QUESTION_ID_PARAM);
        String actionText = request.getParameter(ACTION_PARAM);

        String pageSolveProcess = PageContainer.WEB_INF_COMMON_PASS_TEST_JSP;
        String redirectLoginPage = PageContainer.PATH_PREFIX_REDIRECT +
                PageContainer.PATH_COMMAND_LOGOUT;

        if(ACTION_CANCEL.equals(actionText)){
            removeAttributes(request);
            return redirectLoginPage;
        }

        if ((summaryIdText == null || "".equals(summaryIdText))
                || (questionIdText == null || "".equals(questionIdText))) {
            return redirectLoginPage;
        }

        int summaryId;
        int questionId;
        try {
            summaryId = Integer.parseInt(summaryIdText);
            questionId = Integer.parseInt(questionIdText);
        } catch (NumberFormatException e) {
            return redirectLoginPage;
        }

        Summary summary = (Summary) request.getSession().getAttribute(SUMMARY);
        if (summary != null
                && (summaryId != summary.getId()) ) {
            return redirectLoginPage;
        }else if (summary == null){
            if (summaryId > 0) {
                summary = summaryTestSolveService.getSummaryEntity(summaryId);
                if (summary == null) {
                    return redirectLoginPage;
                }
            } else {
                summary = new Summary();
            }
        }

        Question question = summary.getByLocalId(questionId);
        if(question == null){
            question = summary.getByLocalId(1);
        }

        if (PageContainer.HTTP_POST.equals(request.getMethod())) {
            String submitText = request.getParameter(SUBMIT_PARAM);
            if (SUBMIT_VALUE_SAVE_QUESTION.equals(submitText)){
                saveQuestionData(request, summary, question);
            } else if (SUBMIT_VALUE_SAVE.equals(submitText)) {

                if (!summaryTestSolveService.updateSummaryEntity(summary)) {
                    LOGGER_SLF4J.debug(LoggerMessages.DEBUG_SAVE_TEST_ENTITY_INCOMPLETE);
                    LOGGER_SLF4J.debug(summary.toString());
                    saveSummaryData(request, summary, question);
                    return pageSolveProcess;
                }
                removeAttributes(request);
                return redirectLoginPage;
            }
        }

        saveSummaryData(request, summary, question);
        return pageSolveProcess;
    }

}
