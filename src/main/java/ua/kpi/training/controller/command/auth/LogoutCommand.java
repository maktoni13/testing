package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.command.utility.CommandUtility;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.entity.enums.UserType;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.invalidateCurrentSession(request);
        // CommandUtility.setUserRights(request, "", UserType.GUEST);
        return PageContainer.PATH_PREFIX_REDIRECT +
                PageContainer.PATH_COMMAND_INDEX;
    }
}
