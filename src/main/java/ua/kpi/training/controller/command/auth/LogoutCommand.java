package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.command.utility.CommandUtility;
import ua.kpi.training.controller.resource.PageContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Class Logout Command
 * Logout command implementation
 * @author Anton Makukhin
 */
public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.invalidateCurrentSession(request);
        return PageContainer.PATH_PREFIX_REDIRECT +
                PageContainer.PATH_COMMAND_INDEX;
    }
}
