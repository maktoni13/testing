package ua.kpi.training.controller.command.utility;

import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.entity.UserAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {

    static void setUserRights(HttpServletRequest request,
                              String userName,
                              UserAuthority authority) {
        HttpSession session = request.getSession();
        session.setAttribute(PageContainer.SESSION_USER_NAME, userName);
        session.setAttribute(PageContainer.SESSION_AUTHORITY, authority);
    }

    static boolean checkUserAlreadyLogged(HttpServletRequest request,
                                          String userName) {
        HashSet<?> loggedUsersSource = (HashSet<?>) request.getSession()
                .getServletContext()
                .getAttribute(PageContainer.CONTEXT_LOGGED_USERS);
        HashSet<String> loggedUsers = new HashSet<>();
        loggedUsersSource.forEach(element -> loggedUsers.add((String) element));
        if (loggedUsers.stream().anyMatch(userName::equals)) {
            return true;
        }
        loggedUsers.add(userName);
        request.getSession().getServletContext()
                .setAttribute(PageContainer.CONTEXT_LOGGED_USERS,
                        loggedUsers);
        return false;
    }
}
