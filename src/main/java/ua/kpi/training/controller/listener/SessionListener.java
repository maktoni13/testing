package ua.kpi.training.controller.listener;

import ua.kpi.training.controller.resource.PageContainer;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;
import java.util.Set;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        // TODO: remove <?>. add exception handler
        Set<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute(PageContainer.CONTEXT_LOGGED_USERS);
        String username = (String) httpSessionEvent.getSession()
                .getAttribute(PageContainer.SESSION_USER_NAME);
        loggedUsers.remove(username);
        httpSessionEvent.getSession().setAttribute(
                PageContainer.CONTEXT_LOGGED_USERS, loggedUsers);
    }
}
