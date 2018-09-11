package ua.kpi.training.controller.resource;

/**
 * interface Page Container
 * <p> Contains application pages and commands configuration
 *
 * @author Anton Makukhin
 */
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
    String WEB_INF_TESTS_LIST_JSP = "/WEB-INF/testslist.jsp";
    String WEB_INF_TEST_PASSING_JSP = "/WEB-INF/testpassing.jsp";
    String WEB_INF_ADMIN_THEME_JSP = "/WEB-INF/admin/theme.jsp";
    String WEB_INF_ADMIN_TEST_JSP = "/WEB-INF/admin/test.jsp";
    String WEB_INF_COMMON_PASS_TEST_JSP = "/WEB-INF/passtest.jsp";

    // PATH prefixes
    String PATH_PREFIX_REDIRECT = "redirect:";

    // PATH Commands
    String PATH_COMMAND_USER_PROFILE = "/testing/api/common/userprofile";
    String PATH_COMMAND_ADMIN_PROFILE = "/testing/api/admin/adminprofile";
    String PATH_COMMAND_LOGIN = "/testing/api/login";
    String PATH_COMMAND_LOGOUT = "/testing/api/logout";
    String PATH_COMMAND_INDEX = "/testing/api/index";
    String PATH_COMMAND_THEMES = "/testing/api/common/themes";
    String PATH_COMMAND_TESTS = "/testing/api/common/tests?themeid=";
    String PATH_COMMAND_TEST_SOLVE = "/testing/api/common/testsolve?summaryid=";

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
    String COMMAND_TEST_LIST = "common/tests";
    String COMMAND_PROCESS_THEME = "admin/processtheme";
    String COMMAND_PROCESS_TEST = "admin/processtest";
    String COMMAND_PASSING_TEST = "common/testpass";
    String COMMAND_SOLVING_TEST = "common/testsolve";

    // SECURITY filter
    String SECURITY_FILTER_ADMIN = "admin/";
    String SECURITY_FILTER_COMMON = "common/";

    String PATH_REPLACE_REGEX = ".*/testing/api/";
    String PATH_REPLACE_REPLACEMENT = "";
    String CONTEXT_LOGGED_USERS = "loggedUsers";
    String SESSION_USER_NAME = "username";
    String SESSION_AUTHORITY = "authority";
    String CONTENT_TYPE = "text/html";
    String CHARACTER_ENCODING = "UTF-8";
    String LOGIN_PAGE_ATTR_ERROR_MESSAGE = "loginErrorMessage";
    String LOGIN_PAGE_ATTR_USERNAME = "username";
    String STUDENT_LIST_ATTR = "studentList";
    String THEME_LIST_ATTR = "themeList";
    String TEST_LIST_ATTR = "testList";
}
