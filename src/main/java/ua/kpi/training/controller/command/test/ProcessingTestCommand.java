package ua.kpi.training.controller.command.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.command.utility.CommandUtility;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.logger.LoggerMessages;
import ua.kpi.training.model.entity.Answer;
import ua.kpi.training.model.entity.Question;
import ua.kpi.training.model.entity.Test;
import ua.kpi.training.model.service.TestService;
import ua.kpi.training.model.service.ThemeService;
import ua.kpi.training.model.service.impl.ThemeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class Processing Test Command
 * Implementation of the command for test adding and editing
 * <p> Class provides full cycle of test entity processing (Add, Edit, Delete)
 * @author Anton Makukhin
 */
public class ProcessingTestCommand implements Command {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(ProcessingTestCommand.class);

    private static final String SUBMIT_PARAM = "save";
    private static final String SUBMIT_VALUE_SAVE = "Save test";
    private static final String SUBMIT_VALUE_ADD_QUESTION = "Add question";
    private static final String SUBMIT_VALUE_SAVE_QUESTION = "Save question";
    private static final String TEST_ID_PARAM = "testid";
    private static final String THEME_ID_PARAM = "themeid";
    private static final String REQ_ATTR_TEST = "test";
    private static final String SESSION_ATTR_TEST = "test";
    private static final String REQ_PARAM_TEST_NAME = "testName";
    private static final String REQ_PARAM_TEST_NAME_UA = "testNameUA";
    private static final String REQ_PARAM_TEST_DESC = "testDescription";
    private static final String REQ_PARAM_TEST_DESC_UA = "testDescriptionUA";
    private static final String REQ_PARAM_TEST_INACTIVE = "inactive";
    private static final String REQ_ATTR_ERROR = "savingErrorMessage";
    private static final String SESSION_ATTR_QUESTION = "question";
    private static final String QUESTION_ID_PARAM = "currentquestion";
    private static final String ACTION_PARAM = "action";
    private static final String ACTION_CANCEL = "cancel";
    private static final String ANSWER_DESC = "answerDesc";
    private static final String ANSWER_UA_DESC = "answerDescUA";
    private static final String ANSWERS = "answer";
    private static final String QUEST_DESC = "questionDescription";
    private static final String QUEST_UA_DESC = "questionDescriptionUA";

    private TestService testService;

    public ProcessingTestCommand() {
    }

    public ProcessingTestCommand(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String testIdText = request.getParameter(TEST_ID_PARAM);
        String themeIdText = request.getParameter(THEME_ID_PARAM);
        String questionIdText = request.getParameter(QUESTION_ID_PARAM);
        String actionText = request.getParameter(ACTION_PARAM);
        String pageTestProcess = PageContainer.WEB_INF_ADMIN_TEST_JSP;
        String redirectTestsPage = PageContainer.PATH_PREFIX_REDIRECT +
                PageContainer.PATH_COMMAND_TESTS + themeIdText;
        if(ACTION_CANCEL.equals(actionText)){
            removeAttributes(request);
            return redirectTestsPage;
        }

        if (isIncorrectRequestParams(themeIdText, testIdText, questionIdText)) {
            return redirectTestsPage;
        }

        int testId;
        int themeId;
        int questionId;
        try {
            themeId = Integer.parseInt(themeIdText);
            testId = Integer.parseInt(testIdText);
            questionId = Integer.parseInt(questionIdText);
        } catch (NumberFormatException e) {
            return redirectTestsPage;
        }

        Test test = (Test) request.getSession().getAttribute(SESSION_ATTR_TEST);
        if (test != null
                && (testId != test.getId() || themeId != test.getTheme().getId()) ) {
            return redirectTestsPage;
        }else if (test == null){
            if (testId > 0) {
                test = testService.getTestEntity(testId);
                if (test == null || test.getTheme().getId() != themeId) {
                    return redirectTestsPage;
                }
            } else {
                test = new Test();
            }
        }
        updateTestInfo(test, themeId);

        Question question = test.getQuestionByLocalId(questionId);
        if(question == null){
            question = test.getQuestionByLocalId(1);
        }

        if (PageContainer.HTTP_POST.equals(request.getMethod())) {
            test = extractTestFromRequest(request);
            String submitText = request.getParameter(SUBMIT_PARAM);
            if (SUBMIT_VALUE_ADD_QUESTION.equals(submitText)){
                addNewQuestion(test);
            }else if (SUBMIT_VALUE_SAVE_QUESTION.equals(submitText)){
                saveQuestionData(request, test, question);
            } else if (SUBMIT_VALUE_SAVE.equals(submitText)) {

                if (!testService.saveTestEntity(test)) {
                    LOGGER_SLF4J.debug(LoggerMessages.DEBUG_SAVE_TEST_ENTITY_INCOMPLETE);
                    LOGGER_SLF4J.debug(test.toString());
                    saveTestData(request, test, question);
                    return pageTestProcess;
                }
                removeAttributes(request);
                return redirectTestsPage;
            }
        }

        saveTestData(request, test, question);
        return pageTestProcess;
    }

    private Test extractTestFromRequest(HttpServletRequest request) {
        Test test = (Test) request.getSession().getAttribute(SESSION_ATTR_TEST);
        test.setName(request.getParameter(REQ_PARAM_TEST_NAME));
        test.setNameUA(request.getParameter(REQ_PARAM_TEST_NAME_UA));
        test.setDescription(request.getParameter(REQ_PARAM_TEST_DESC));
        test.setDescriptionUA(request.getParameter(REQ_PARAM_TEST_DESC_UA));
        String[] inactive = request.getParameterValues(REQ_PARAM_TEST_INACTIVE);
        test.setInactive((inactive != null) && inactive.length > 0
                && "on".equals(inactive[0]));
        return test;
    }

    private void saveTestData(HttpServletRequest request, Test test, Question question) {
        HttpSession session = request.getSession();
        request.setAttribute(REQ_ATTR_TEST, test);
        session.setAttribute(SESSION_ATTR_TEST, test);
        request.setAttribute(SESSION_ATTR_QUESTION, question);
        session.setAttribute(SESSION_ATTR_QUESTION, question);
        request.setAttribute(REQ_ATTR_ERROR, test.getValidationResult());
        test.setValidationResult(new StringBuilder());
    }

    private boolean isIncorrectRequestParams(String themeIdText, String testIdText, String questionIdText){
        return (themeIdText == null || "".equals(themeIdText))
                || (testIdText == null || "".equals(testIdText))
                || (questionIdText == null || "".equals(questionIdText));
    }

    private void updateTestInfo(Test test, int themeId){
        if (test.getTheme() == null){
            ThemeService themeService = new ThemeServiceImpl();
            test.setTheme(themeService.getThemeEntity(themeId));
        }
        if (test.getQuestions() == null || test.getQuestions().size() == 0){
            addNewQuestion(test);
        }
    }

    private void saveQuestionData(HttpServletRequest request, Test test, Question question){
        List<String> correctAnswers;
        String[] correct = request.getParameterValues(ANSWERS);
        if (correct == null) {
            correctAnswers = new ArrayList<>();
        } else {
            correctAnswers = Arrays.asList(correct);
        }

        for (Answer answer : question.getAnswers()) {
            answer.setDescription(request.getParameter(
                    ANSWER_DESC + answer.getIdLocal()));
            answer.setDescriptionUA(request.getParameter(
                    ANSWER_UA_DESC + answer.getIdLocal()));
            answer.setCorrect(correctAnswers.contains(Integer.toString(answer.getIdLocal())));

        }
        question.setDescription(request.getParameter(QUEST_DESC));
        question.setDescriptionUA(request.getParameter(QUEST_UA_DESC));
    }

    private void addNewQuestion(Test test){
        if (test.getQuestions() == null){
            test.setQuestions(new ArrayList<>());
        }
        Question question = new Question();
        question.setTest(test);
        question.setIdLocal(test.getQuestions().size() + 1);
        List<Question> questionList = test.getQuestions();
        List<Answer> answerList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Answer answer = new Answer();
            answer.setIdLocal(i + 1);
            answer.setQuestion(question);
            answerList.add(answer);
        }
        question.setAnswers(answerList);
        questionList.add(question);
    }

    private void removeAttributes(HttpServletRequest request){
        List<String> attributeList = Arrays.asList(REQ_ATTR_TEST, SESSION_ATTR_QUESTION);
        CommandUtility.removeRequestAttributes(request, attributeList);
        CommandUtility.removeSessionAttributes(request, attributeList);
    }

}
