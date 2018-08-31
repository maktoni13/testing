package ua.kpi.training.model.dao.mapper;

import ua.kpi.training.model.entity.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TestMapper implements ObjectMapper<Test> {
    private static final String ID_COLUMN = "id";
    private static final String USER_NAME_COLUMN = "username";
    private static final String PASSWORD_COLUMN = "password";
    private static final String EMAIL_COLUMN = "email";
    private static final String FIRST_NAME_COLUMN = "first_name";
    private static final String FIRST_NAME_UA_COLUMN = "first_name_ua";
    private static final String LAST_NAME_COLUMN = "last_name";
    private static final String LAST_NAME_UA_COLUMN = "last_name_ua";
    private static final String ENABLED_FLAG_COLUMN = "enabled_flag";
    private static final String ADMIN_FLAG_COLUMN = "admin_flag";

    @Override
    public Test extractFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public Test makeUnique(Map<Integer, Test> map, Test objectT) {
        return null;
    }
}
