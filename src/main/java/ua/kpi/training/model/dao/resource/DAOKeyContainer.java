package ua.kpi.training.model.dao.resource;

public interface DAOKeyContainer {
    String DAO_PROPERTIES = "dao";
    String SELECT_USER_BY_USERNAME = "select.user.by.username";
    String SELECT_USER_BY_EMAIL = "select.user.by.email";
    String INSERT_USER = "insert.user.into.user.table";
    String SELECT_ALL_USERS = "select.user.all";
    String SELECT_ALL_THEMES = "select.theme.all";
    String SELECT_THEME_BY_ID = "select.theme.by.id";
    String SELECT_ALL_TESTS = "select.test.all";
    String SELECT_TESTS_BY_THEME_ID = "select.tests.by.theme.id";
}
