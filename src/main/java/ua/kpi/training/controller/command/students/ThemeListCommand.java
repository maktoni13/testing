package ua.kpi.training.controller.command.students;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.CommonService;
import ua.kpi.training.model.service.ThemeService;
import ua.kpi.training.model.service.impl.ThemeServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ThemeListCommand implements Command {
    @Override
    public CommonService getService() {
        return new ThemeServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request) {
        ThemeService themeService = (ThemeService) getService();
        request.setAttribute(PageContainer.THEME_LIST_ATTR,
                themeService.getAllThemes());
        return PageContainer.PAGE_THEME_LIST;
    }
}
