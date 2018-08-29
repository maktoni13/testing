package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.command.utility.CommandUtility;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.entity.UserAuthority;
import ua.kpi.training.model.service.CommonService;

import javax.servlet.http.HttpServletRequest;

public class ExceptionCommand implements Command {

    @Override
    public CommonService getService() {
        return null;
    }

    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.setUserRights(request, "", UserAuthority.GUEST);
        return PageContainer.ERROR_PAGE_PATH; //TODO: Need to create exception page with error details for user
    }
}
