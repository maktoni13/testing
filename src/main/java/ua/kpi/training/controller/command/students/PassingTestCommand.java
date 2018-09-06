package ua.kpi.training.controller.command.students;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.entity.Question;
import ua.kpi.training.model.entity.Test;
import ua.kpi.training.model.service.PassingTestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PassingTestCommand implements Command{
    PassingTestService passingTestService;
    private static final String TEST_ID_PARAM = "testid";
    private static final String TEST_SESSION_ATTR = "testPass";
    private static final String QUESTION_CURRENT_ID_PARAM = "currentquestion";
    private static final String QUESTION_REQUEST_ATTR = "question";

    public PassingTestCommand() {
    }

    public PassingTestCommand(PassingTestService passingTestService) {
        this.passingTestService = passingTestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Test test = (Test) session.getAttribute(TEST_SESSION_ATTR);
        if (test == null){
            Integer testId;
            String testIdText = request.getParameter(TEST_ID_PARAM);
            try {
                testId = Integer.parseInt(testIdText);
            } catch (NumberFormatException e){
                return PageContainer.PATH_PREFIX_REDIRECT +
                        PageContainer.PATH_COMMAND_THEMES;
            }
            test = passingTestService.getTestById(testId);
            session.setAttribute(TEST_SESSION_ATTR, test);
        }
        String questionIdText = request.getParameter(QUESTION_CURRENT_ID_PARAM);

        Question question;
        if(questionIdText == null){
            question = test.getByLocalId(1);
        } else {
            question = test.getById(Integer.parseInt(questionIdText));
        }
        request.setAttribute(QUESTION_REQUEST_ATTR, question);
        return PageContainer.WEB_INF_TEST_PASSING_JSP;
    }
}
