package ua.kpi.training.controller.listener;

import ua.kpi.training.controller.resource.PageContainer;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<?> loggedUsers = (HashSet<?>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute(PageContainer.CONTEXT_LOGGED_USERS);
        String userName = (String) httpSessionEvent.getSession()
                .getAttribute(PageContainer.SESSION_USER_NAME);
        loggedUsers.remove(userName);
        httpSessionEvent.getSession().setAttribute(
                PageContainer.CONTEXT_LOGGED_USERS, loggedUsers);
    }
}
