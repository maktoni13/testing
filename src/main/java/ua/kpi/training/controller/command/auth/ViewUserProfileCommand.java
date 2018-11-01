package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.ViewUserProfileService;
import ua.kpi.training.model.service.impl.ViewUserProfileServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * Class View User Profile Command
 * View User Profile Command Implementation
 * @author Anton Makukhin
 */
public class ViewUserProfileCommand implements Command {
    private ViewUserProfileService viewUserProfileService;

    public ViewUserProfileCommand() {
        this.viewUserProfileService = new ViewUserProfileServiceImpl();
    }

    public ViewUserProfileCommand(ViewUserProfileService viewUserProfileService) {
        this.viewUserProfileService = viewUserProfileService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        return PageContainer.WEB_INF_USER_USER_PROFILE_JSP;
    }
}
