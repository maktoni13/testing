package ua.kpi.training.controller.resource;

public interface PageContainer {
    String INDEX_PAGE_PATH = "index.jsp";
    String LOGIN_PAGE_PATH = "login.jsp";
    String PATH_REPLACE_REGEX = ".*/testing/";
    String PATH_REPLACE_REPLACEMENT = "";
    String CONTEXT_LOGGED_USERS = "loggedUsers";
    String COMMAND_LOGIN = "login";
    String COMMAND_LOGOUT = "logout";
    String COMMAND_EXCEPTION = "exception";
    String SESSION_USER_NAME = "userName";
    String SESSION_AUTHORITY = "authority";
    String CONTENT_TYPE = "text/html";
    String CHARACTER_ENCODING = "UTF-8";
    String PARAMETER_USER_NAME = "username";
    String PARAMETER_PASSWORD = "password";
}
