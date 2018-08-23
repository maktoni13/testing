package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.ICommand;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.ILogInService;

import javax.servlet.http.HttpServletRequest;

public class LogInCommand implements ICommand {
    private ILogInService logInService;

    public LogInCommand() {
    }

    public LogInCommand(ILogInService logInService) {
        this.logInService = logInService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String userName = request.getParameter(PageContainer.PARAMETER_USER_NAME);
        String password = request.getParameter(PageContainer.PARAMETER_PASSWORD);


        return null;
    }
}
