package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.command.dto.UserDTO;
import ua.kpi.training.controller.command.utility.CommandUtility;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.entity.enums.UserType;
import ua.kpi.training.model.service.LoginService;
import ua.kpi.training.view.resource.MessageBundle;
import ua.kpi.training.view.resource.MessageKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private LoginService loginService;

    public LoginCommand() {
    }

    public LoginCommand(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String username = request.getParameter(PageContainer.PARAMETER_USER_NAME);
        String password = request.getParameter(PageContainer.PARAMETER_PASSWORD);
        HttpSession session = request.getSession();
        // TODO: Add logout message

        if (username == null || username.equals("") || password == null || password.equals("")) {
            // TODO: Login error message
            return PageContainer.WEB_INF_LOGIN_JSP;
        }

        if (CommandUtility.checkUserAlreadyLogged(request, username)) {
            // TODO: Error Message
            return PageContainer.WEB_INF_ERROR_JSP;
        }

        UserDTO userDTO = loginService.getUserDTOUsernamePassword(username, password);
        if (!userDTO.isExists()) {
            session.setAttribute(PageContainer.SESSION_INCORRECT_LOGIN_PASSWORD,
                    MessageBundle.getMessage(MessageKey.INCORRECT_USERNAME_OR_PASSWORD));
            return PageContainer.WEB_INF_LOGIN_JSP;
        } else if (!userDTO.isValidPassword()) {
            session.setAttribute(PageContainer.SESSION_INCORRECT_LOGIN_PASSWORD,
                    MessageBundle.getMessage(MessageKey.INCORRECT_USERNAME_OR_PASSWORD));
            return PageContainer.WEB_INF_LOGIN_JSP;
        } else if (!userDTO.isEnabled()) {
            session.setAttribute(PageContainer.SESSION_INCORRECT_LOGIN_PASSWORD,
                    MessageBundle.getMessage(MessageKey.USER_IS_DISABLED));
            return PageContainer.WEB_INF_LOGIN_JSP;
        }
        CommandUtility.setUserRights(request, userDTO.getUsername(),
                userDTO.getAuthority());

        return PageContainer.PATH_PREFIX_REDIRECT +
                (userDTO.getAuthority().equals(UserType.ADMIN) ?
                        PageContainer.PATH_COMMAND_ADMIN_PROFILE :
                        PageContainer.PATH_COMMAND_USER_PROFILE);
    }
}
