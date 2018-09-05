package ua.kpi.training.controller.resource;

public interface PageContainer {

    // direct WEB-INF paths
    String WEB_INF_INDEX_JSP = "/WEB-INF/index.jsp";
    String WEB_INF_REGISTRATION_JSP = "/WEB-INF/registration.jsp";
    String WEB_INF_LOGIN_JSP = "/WEB-INF/login.jsp";
    String WEB_INF_ERROR_JSP = "/WEB-INF/error.jsp";
    String WEB_INF_USER_USER_PROFILE_JSP = "/WEB-INF/user/userprofile.jsp";
    String WEB_INF_ADMIN_ADMIN_PROFILE_JSP = "/WEB-INF/admin/adminprofile.jsp";
    String WEB_INF_ADMIN_STUDENTS_LIST_JSP = "/WEB-INF/admin/studentslist.jsp";
    String WEB_INF_THEMES_LIST_JSP = "/WEB-INF/themeslist.jsp";

    // PATH prefixes
    String PATH_PREFIX_REDIRECT = "redirect:";

    // PATH Commands
    String PATH_COMMAND_USER_PROFILE = "/testing/api/common/userprofile";
    String PATH_COMMAND_ADMIN_PROFILE = "/testing/api/admin/adminprofile";
    String PATH_COMMAND_LOGIN = "/testing/api/login";
    String PATH_COMMAND_INDEX = "/testing/api/index";

    // HTTP METHOD
    String HTTP_GET = "GET";
    String HTTP_POST = "POST";

    // COMMAND
    String COMMAND_LOGIN = "login";
    String COMMAND_LOGOUT = "logout";
    String COMMAND_REGISTRATION = "registration";
    String COMMAND_EXCEPTION = "exception";
    String COMMAND_VIEW_USER_PROFILE = "common/userprofile";
    String COMMAND_THEME_LIST = "common/themes";
    String COMMAND_VIEW_ADMIN_PROFILE = "admin/adminprofile";
    String COMMAND_STUDENT_LIST = "admin/students";
    String COMMAND_INDEX_PAGE = "index";

    // SECURITY filter
    String SECURITY_FILTER_ADMIN = "admin/";
    String SECURITY_FILTER_COMMON = "common/";


    String PATH_REPLACE_REGEX = ".*/testing/api/";
    String PATH_REPLACE_REPLACEMENT = "";
    String CONTEXT_LOGGED_USERS = "loggedUsers";
    String SESSION_USER_NAME = "username";
    String SESSION_AUTHORITY = "authority";
    String SESSION_INCORRECT_LOGIN_PASSWORD = "";
    String CONTENT_TYPE = "text/html";
    String CHARACTER_ENCODING = "UTF-8";
    String PARAMETER_USER_NAME = "username";
    String PARAMETER_PASSWORD = "password";
    String REG_PARAM_USERNAME = "username";
    String REG_PARAM_PASSWORD = "password";
    String REG_PARAM_CONFIRM_PASSWORD = "confirm-password";
    String REG_PARAM_EMAIL = "email";
    String REG_PARAM_CONFIRM_EMAIL = "confirm-email";
    String REG_PARAM_FIRST_NAME = "first-name";
    String REG_PARAM_FIRST_NAME_UA = "first-name-ua";
    String REG_PARAM_LAST_NAME = "last-name";
    String REG_PARAM_LAST_NAME_UA = "last-name-ua";
    String REG_ATTR_USERNAME = "username";
    String REG_ATTR_PASSWORD = "password";
    String REG_ATTR_CONFIRM_PASSWORD = "confirmPassword";
    String REG_ATTR_EMAIL = "email";
    String REG_ATTR_CONFIRM_EMAIL = "confirmEmail";
    String REG_ATTR_FIRST_NAME = "firstName";
    String REG_ATTR_FIRST_NAME_UA = "firstNameUA";
    String REG_ATTR_LAST_NAME = "lastName";
    String REG_ATTR_LAST_NAME_UA = "lastNameUA";
    String REG_ATTR_ERROR_MESSAGE = "registrationErrorMessage";
    String LOGIN_PAGE_ATTR_ERROR_MESSAGE = "loginErrorMessage";
    String LOGIN_PAGE_ATTR_USERNAME = "username";
    String STUDENT_LIST_ATTR = "studentList";
    String THEME_LIST_ATTR = "themeList";
}
