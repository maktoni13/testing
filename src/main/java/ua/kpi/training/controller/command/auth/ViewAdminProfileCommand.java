package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.ViewAdminProfileService;
import ua.kpi.training.model.service.impl.ViewAdminProfileServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * Class View Admin Profile Command
 * View Admin Profile Command Implementation
 * @author Anton Makukhin
 */
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
        return PageContainer.WEB_INF_ADMIN_ADMIN_PROFILE_JSP;
    }
}
