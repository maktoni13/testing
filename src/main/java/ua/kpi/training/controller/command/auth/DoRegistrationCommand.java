package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;

import javax.servlet.http.HttpServletRequest;

public class DoRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return PageContainer.REGISTRATION_PAGE_PATH;
    }
}
