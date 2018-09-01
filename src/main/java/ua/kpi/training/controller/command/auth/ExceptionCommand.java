package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;

import javax.servlet.http.HttpServletRequest;

public class ExceptionCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return PageContainer.WEB_INF_ERROR_JSP; //TODO: Need to create exception page with error details for user
    }
}
