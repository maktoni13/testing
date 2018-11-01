package ua.kpi.training.controller.command.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.TestService;

import javax.servlet.http.HttpServletRequest;

/**
 * Class Test Passing Command
 * Implementation of command for starting test solving process
 * Firstly we create summary and then redirect to page for solving
 * @author Anton Makukhin
 */
public class TestPassingCommand implements Command {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(TestPassingCommand.class);

    private static final String TEST_ID = "testid";
    private static final String THEME_ID = "themeid";
    private static final String USERNAME = "username";

    private TestService testService;

    public TestPassingCommand() {
    }

    public TestPassingCommand(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String testIdText = request.getParameter(TEST_ID);
        String themeIdText = request.getParameter(THEME_ID);
        String redirectTestsPage = PageContainer.PATH_PREFIX_REDIRECT +
                PageContainer.PATH_COMMAND_TESTS + themeIdText;
        if ((themeIdText == null || "".equals(themeIdText))
                || (testIdText == null || "".equals(testIdText))){
            return redirectTestsPage;
        }
        int testId;
        try {
            testId = Integer.parseInt(testIdText);
        } catch (NumberFormatException e) {
            return redirectTestsPage;
        }
        String username = (String) request.getSession().getAttribute(USERNAME);
        int summaryId = testService.prepareSummaryForTestPassing(testId, username);
        if (summaryId > 0){
            return PageContainer.PATH_PREFIX_REDIRECT +
                    PageContainer.PATH_COMMAND_TEST_SOLVE + summaryId + "&questionid=1";
        }
        return redirectTestsPage;
    }
}
