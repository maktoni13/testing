package ua.kpi.training.controller.command.auth;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.command.dto.UserDTO;
import ua.kpi.training.controller.command.utility.CommandUtility;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.entity.enums.UserType;
import ua.kpi.training.model.service.LoginService;

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
        // if ((session != null) &&
//                 session.getAttribute(PageContainer.SESSION_USER_NAME) != null){
//             // TODO: You should logout message
//             return PageContainer.;
//         }

        if (username == null || username.equals("") || password == null || password.equals("")) {
            // TODO: Login error message
            return PageContainer.LOGIN_PAGE_PATH;
        }

        if (CommandUtility.checkUserAlreadyLogged(request, username)) {
            // TODO: Error Message
            return PageContainer.ERROR_PAGE_PATH;
        }

        UserDTO userDTO = loginService.getUserDTOUsernamePassword(username, password);
        if (!userDTO.isExists()) {
            session.setAttribute(PageContainer.SESSION_INCORRECT_LOGIN_PASSWORD,
                    "Incorrect username or password"); // TODO: Message Bundle
            return PageContainer.LOGIN_PAGE_PATH;
        } else if (!userDTO.isValidPassword()){
            session.setAttribute(PageContainer.SESSION_INCORRECT_LOGIN_PASSWORD,
                    "Incorrect username or password"); // TODO: Message Bundle
            return PageContainer.LOGIN_PAGE_PATH;
        } else if (!userDTO.isEnabled()){
            session.setAttribute(PageContainer.SESSION_INCORRECT_LOGIN_PASSWORD,
                    "User is disabled. Please connect administrator"); // TODO: Message Bundle
            return PageContainer.LOGIN_PAGE_PATH;
        }
        CommandUtility.setUserRights(request, userDTO.getUsername(),
            userDTO.getAuthority());

        return userDTO.getAuthority().equals(UserType.ADMIN) ?
                PageContainer.ADMIN_PROFILE_COMMAND_PATH :
                PageContainer.USER_PROFILE_COMMAND_PATH;
    }
}
