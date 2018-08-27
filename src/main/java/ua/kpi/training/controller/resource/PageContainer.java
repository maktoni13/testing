package ua.kpi.training.controller.resource;

public interface PageContainer {
    String INDEX_PAGE_PATH = "/index.jsp";
    String LOGIN_PAGE_PATH = "/login.jsp";
    String ERROR_PAGE_PATH = "/WEB-INF/error.jsp";
    String USER_PROFILE_PATH = "/WEB-INF/user/userbasis.jsp";
    // String PATH_REPLACE_REGEX = ".*/testing/";
    String PATH_REPLACE_REGEX = ".*/";
    String PATH_REPLACE_REPLACEMENT = "";
    String CONTEXT_LOGGED_USERS = "loggedUsers";
    String COMMAND_LOGIN = "login";
    String COMMAND_LOGOUT = "logout";
    String COMMAND_EXCEPTION = "exception";
    String SESSION_USER_NAME = "username";
    String SESSION_AUTHORITY = "authority";
    String SESSION_INCORRECT_LOGIN_PASSWORD = "";
    String CONTENT_TYPE = "text/html";
    String CHARACTER_ENCODING = "UTF-8";
    String PARAMETER_USER_NAME = "username";
    String PARAMETER_PASSWORD = "password";
    String ADMIN_PROFILE_PATH = "/WEB-INF/admin/adminbasis.jsp";
}
