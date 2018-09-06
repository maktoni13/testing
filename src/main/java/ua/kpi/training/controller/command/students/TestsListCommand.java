package ua.kpi.training.controller.command.students;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.TestService;

import javax.servlet.http.HttpServletRequest;

public class TestsListCommand implements Command {
    private TestService testService;
    private static final String THEME_ID_PARAM = "themeid";

    public TestsListCommand() {
    }

    public TestsListCommand(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String themeIdText = request.getParameter(THEME_ID_PARAM);
        if (themeIdText == null){

        }
        Integer themeId;
        try {
            themeId = Integer.parseInt(themeIdText);
        } catch (NumberFormatException e){
            return PageContainer.PATH_PREFIX_REDIRECT +
                    PageContainer.PATH_COMMAND_THEMES;
        }
        request.setAttribute(PageContainer.TEST_LIST_ATTR,
                testService.getTestsListByThemeId(themeId));
        request.setAttribute(THEME_ID_PARAM, themeIdText);
        return PageContainer.WEB_INF_TESTS_LIST_JSP;


    }
}
