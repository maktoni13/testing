package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.ICommand;
import ua.kpi.training.controller.resource.PageContainer;

import javax.servlet.http.HttpServletRequest;

public class ExceptionCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request) {
        return PageContainer.LOGIN_PAGE_PATH; //TODO: Need to create exception page with error details for user
    }
}
