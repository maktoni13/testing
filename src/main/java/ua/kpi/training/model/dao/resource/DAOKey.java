package ua.kpi.training.model.dao.resource;

public interface DAOKey {
    String DAO_PROPERTIES = "dao";

    String SELECT_USER_BY_USERNAME = "select.user.by.username";
    String SELECT_USER_BY_EMAIL = "select.user.by.email";
    String SELECT_ALL_USERS = "select.user.all";
    String SELECT_ALL_THEMES = "select.theme.all";
    String SELECT_THEME_BY_ID = "select.theme.by.id";
    String SELECT_ALL_TESTS = "select.test.all";
    String SELECT_TESTS_BY_THEME_ID = "select.tests.by.theme.id";
    String SELECT_TEST_BY_ID = "select.test.by.id";
    String SELECT_QUESTIONS_BY_THEME_ID = "select.questions.by.test.id";

    String INSERT_USER = "insert.user.into.user.table";
    String INSERT_THEME = "insert.theme.into.theme.table";
    String INSERT_TEST = "insert.test.into.test.table";
    String INSERT_ANSWER_BY_LIST = "insert.answer.by.list";
    String INSERT_QUESTION_BY_LIST = "insert.question.by.list";

    String UPDATE_THEME = "update.theme.into.theme.table";
    String UPDATE_TEST = "update.test.into.test.table";

    String DELETE_ANSWER_BY_LIST = "delete.answer.by.list";
    String DELETE_QUESTION_BY_LIST = "delete.question.by.list";
}
