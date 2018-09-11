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

/**
 * Class Login Command
 * Login command implementation
 * @author Anton Makukhin
 */
public class LoginCommand implements Command {
    private static final String PARAMETER_USER_NAME = "username";
    private static final String PARAMETER_PASSWORD = "password";

    private LoginService loginService;

    public LoginCommand() {
    }

    public LoginCommand(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String page = PageContainer.WEB_INF_LOGIN_JSP;
        if (PageContainer.HTTP_GET.equals(request.getMethod())){
            return page;
        }
        String username = request.getParameter(PARAMETER_USER_NAME);
        String password = request.getParameter(PARAMETER_PASSWORD);

        request.setAttribute(PageContainer.LOGIN_PAGE_ATTR_USERNAME, username);

        HttpSession session = request.getSession();
        String usernameSessionAttr = (String) session.getAttribute(PageContainer.SESSION_USER_NAME);
        if (usernameSessionAttr != null && !usernameSessionAttr.equals("")){
            request.setAttribute(PageContainer.LOGIN_PAGE_ATTR_ERROR_MESSAGE,
                    MessageBundle.getMessage(MessageKey.LOGIN_SHOULD_LOGOUT_FIRST));
            return page;
        }

        if (username == null || username.equals("") || password == null || password.equals("")) {
            request.setAttribute(PageContainer.LOGIN_PAGE_ATTR_ERROR_MESSAGE,
                    MessageBundle.getMessage(MessageKey.LOGIN_EMPTY_ERROR));
            return page;
        }

        if (CommandUtility.checkUserAlreadyLogged(request, username)) {
            request.setAttribute(PageContainer.LOGIN_PAGE_ATTR_ERROR_MESSAGE,
                    MessageBundle.getMessage(MessageKey.LOGIN_USER_ALREADY_LOGGED_ERROR));
            return page;
        }

        UserDTO userDTO = loginService.getUserDTOUsernamePassword(username, password);
        if (!userDTO.isExists()) {
            request.setAttribute(PageContainer.LOGIN_PAGE_ATTR_ERROR_MESSAGE,
                    MessageBundle.getMessage(MessageKey.INCORRECT_USERNAME_OR_PASSWORD));
            return page;
        } else if (!userDTO.isValidPassword()) {
            request.setAttribute(PageContainer.LOGIN_PAGE_ATTR_ERROR_MESSAGE,
                    MessageBundle.getMessage(MessageKey.INCORRECT_USERNAME_OR_PASSWORD));
            return page;
        } else if (!userDTO.isEnabled()) {
            request.setAttribute(PageContainer.LOGIN_PAGE_ATTR_ERROR_MESSAGE,
                    MessageBundle.getMessage(MessageKey.USER_IS_DISABLED));
            return page;
        }
        CommandUtility.setUserRights(request, userDTO.getUsername(),
                userDTO.getAuthority());

        return PageContainer.PATH_PREFIX_REDIRECT +
                (userDTO.getAuthority().equals(UserType.ADMIN) ?
                        PageContainer.PATH_COMMAND_ADMIN_PROFILE :
                        PageContainer.PATH_COMMAND_USER_PROFILE);
    }

}
