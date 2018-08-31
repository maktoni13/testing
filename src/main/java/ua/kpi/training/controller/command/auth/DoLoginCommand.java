package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;

import javax.servlet.http.HttpServletRequest;

public class DoLoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        // TODO: redirect if logged not from direct link
        return PageContainer.LOGIN_PAGE_PATH;
    }
}
