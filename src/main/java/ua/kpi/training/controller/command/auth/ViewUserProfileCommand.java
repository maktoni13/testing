package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.ViewUserProfileService;
import ua.kpi.training.model.service.impl.ViewUserProfileServiceImpl;

import javax.servlet.http.HttpServletRequest;

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
        // TODO: Add user profile data receiving
        return PageContainer.USER_PROFILE_PATH;
    }
}
