package ua.kpi.training.controller.command.students;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.ThemeService;

import javax.servlet.http.HttpServletRequest;

public class ThemeListCommand implements Command {
    ThemeService themeService;

    public ThemeListCommand() {
    }

    public ThemeListCommand(ThemeService themeService) {
        this.themeService = themeService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(PageContainer.THEME_LIST_ATTR,
                themeService.getAllThemes());
        return PageContainer.WEB_INF_THEMES_LIST_JSP;
    }
}
