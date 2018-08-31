package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.ViewAdminProfileService;
import ua.kpi.training.model.service.impl.ViewAdminProfileServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class ViewAdminProfileCommand implements Command {
    private ViewAdminProfileService viewAdminProfileService;

    public ViewAdminProfileCommand() {
        this.viewAdminProfileService = new ViewAdminProfileServiceImpl();
    }

    public ViewAdminProfileCommand(ViewAdminProfileService viewAdminProfileService) {
        this.viewAdminProfileService = viewAdminProfileService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        // TODO: Add user profile data receiving
        return PageContainer.ADMIN_PROFILE_PATH;
    }
}
