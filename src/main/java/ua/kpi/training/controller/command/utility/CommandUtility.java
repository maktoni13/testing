package ua.kpi.training.controller.command.utility;

import com.sun.deploy.net.HttpRequest;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.entity.UserAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {

    public static void setUserRights(HttpServletRequest request,
                              String username,
                              UserAuthority authority) {
        HttpSession session = request.getSession();
        session.setAttribute(PageContainer.SESSION_USER_NAME, username);
        session.setAttribute(PageContainer.SESSION_AUTHORITY, authority);
    }

    public static boolean checkUserAlreadyLogged(HttpServletRequest request,
                                          String username) {
        HashSet<?> loggedUsersSource = (HashSet<?>) request.getSession()
                .getServletContext()
                .getAttribute(PageContainer.CONTEXT_LOGGED_USERS);
        HashSet<String> loggedUsers = new HashSet<>();
        loggedUsersSource.forEach(element -> loggedUsers.add((String) element));
        if (loggedUsers.stream().anyMatch(username::equals)) {
            return true;
        }
        loggedUsers.add(username);
        request.getSession().getServletContext()
                .setAttribute(PageContainer.CONTEXT_LOGGED_USERS,
                        loggedUsers);
        return false;
    }

    public static void invalidateCurrentSession(HttpServletRequest request){
        HttpSession currentSession = request.getSession();
        if(currentSession != null){
            currentSession.invalidate();
        }
    }

    public static void setIncorrectUsernamePassword(HttpServletRequest request,
                                               String username) {
        HttpSession session = request.getSession();
        session.setAttribute(PageContainer.SESSION_USER_NAME, username);

    }
}
