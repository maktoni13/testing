package ua.kpi.training.controller.command.utility;

import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.entity.enums.UserType;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class CommandUtility {

    public static void setUserRights(HttpServletRequest request,
                              String username,
                              UserType authority) {
        HttpSession session = request.getSession();
        session.setAttribute(PageContainer.SESSION_USER_NAME, username);
        session.setAttribute(PageContainer.SESSION_AUTHORITY, authority);
    }

    public static boolean checkUserAlreadyLogged(HttpServletRequest request,
                                          String username) {
        Set<String> loggedUsersSource = (HashSet<String>) request.getSession()
                .getServletContext()
                .getAttribute(PageContainer.CONTEXT_LOGGED_USERS);
        Set<String> loggedUsers = new HashSet<>();
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
            ServletContext servletContext = currentSession.getServletContext();
            Set<String> loggedUsers = (HashSet<String>) servletContext
                    .getAttribute(PageContainer.CONTEXT_LOGGED_USERS);
            loggedUsers.remove((String) currentSession.getAttribute(PageContainer.SESSION_USER_NAME));
            servletContext.setAttribute(PageContainer.CONTEXT_LOGGED_USERS,
                    loggedUsers);
            currentSession.invalidate();
        }
    }

    public static void setIncorrectUsernamePassword(HttpServletRequest request,
                                               String username) {
        HttpSession session = request.getSession();
        session.setAttribute(PageContainer.SESSION_USER_NAME, username);

    }
}
