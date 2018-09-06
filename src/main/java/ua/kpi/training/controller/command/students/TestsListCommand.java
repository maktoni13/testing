package ua.kpi.training.controller.command.students;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.command.dto.TestsListByThemeDTO;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.entity.Test;
import ua.kpi.training.model.entity.Theme;
import ua.kpi.training.model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TestsListCommand implements Command {
    private TestService testService;
    private static final String THEME_ID_PARAM = "themeid";
    private static final String THEME_REQUEST_ATTR = "theme";

    public TestsListCommand() {
    }

    public TestsListCommand(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Theme theme = (Theme) request.getAttribute(THEME_REQUEST_ATTR);
        Integer themeId;
        if (theme != null){
            themeId = theme.getId();
        } else {
            String themeIdText = request.getParameter(THEME_ID_PARAM);
            try {
                themeId = Integer.parseInt(themeIdText);
            } catch (NumberFormatException e){
                return PageContainer.PATH_PREFIX_REDIRECT +
                        PageContainer.PATH_COMMAND_THEMES;
            }
        }
        TestsListByThemeDTO testsDTO = testService.getTestsListByThemeId(themeId);

        request.setAttribute(PageContainer.TEST_LIST_ATTR,
                testsDTO.getTestList());
        request.setAttribute(THEME_REQUEST_ATTR, testsDTO.getTheme());
        return PageContainer.WEB_INF_TESTS_LIST_JSP;


    }
}
