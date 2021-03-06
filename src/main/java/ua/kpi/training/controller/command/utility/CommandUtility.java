package ua.kpi.training.controller.command.utility;

import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.entity.enums.UserType;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class Command Utility
 * <p> Helper class for some common command actions
 *
 * @author Anton Makukhin
 */
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
        Set<String> loggedUsers = getLoggedUsers(
                request.getSession().getServletContext());
        return loggedUsers.stream().anyMatch(username::equals);
    }

    public static void invalidateCurrentSession(HttpServletRequest request){
        HttpSession currentSession = request.getSession();
        if(currentSession != null){
            ServletContext servletContext = currentSession.getServletContext();
            Set<String> loggedUsers = getLoggedUsers(servletContext);
            loggedUsers.remove(getUserFromSession(currentSession));
            servletContext.setAttribute(PageContainer.CONTEXT_LOGGED_USERS,
                    loggedUsers);
            currentSession.invalidate();
        }
    }

    public static void removeRequestAttributes(HttpServletRequest request, List<String> attributesList){
        if (request != null && attributesList != null){
            attributesList.forEach(request::removeAttribute);
        }
    }

    public static void removeSessionAttributes(HttpServletRequest request, List<String> attributesList){
        if (request != null && attributesList != null){
            attributesList.forEach(request::removeAttribute);
        }
    }

    private static String getUserFromSession(HttpSession session){
        return (String) session.getAttribute(PageContainer.SESSION_USER_NAME);
    }

    private static Set<String> getLoggedUsers(ServletContext servletContext){
        Set<?> loggedUsersObj = (HashSet<?>) servletContext
                .getAttribute(PageContainer.CONTEXT_LOGGED_USERS);
        Set<String> loggedUsers = new HashSet<>();
        loggedUsersObj.forEach(element -> loggedUsers.add((String) element));

        return loggedUsers;
    }


}
