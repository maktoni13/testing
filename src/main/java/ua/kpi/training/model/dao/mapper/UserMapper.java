package ua.kpi.training.model.dao.mapper;

import ua.kpi.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Class User mapper
 * <p> Mapper for object User from user database table
 *
 * @author Anton Makukhin
 */
public class UserMapper implements ObjectMapper<User> {
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
    private static final String TESTS_COMPLETED_COLUMN = "tests_completed";
    private static final String AVERAGE_EVALUATION_COLUMN = "average_evaluation";

    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ID_COLUMN));
        user.setUsername(resultSet.getString(USER_NAME_COLUMN));
        user.setPassword(resultSet.getString(PASSWORD_COLUMN));
        user.setEmail(resultSet.getString(EMAIL_COLUMN));
        user.setFirstName(resultSet.getString(FIRST_NAME_COLUMN));
        user.setFirstNameUA(resultSet.getString(FIRST_NAME_UA_COLUMN));
        user.setLastName(resultSet.getString(LAST_NAME_COLUMN));
        user.setLastNameUA(resultSet.getString(LAST_NAME_UA_COLUMN));
        user.setEnabled(resultSet.getBoolean(ENABLED_FLAG_COLUMN));
        user.setAdmin(resultSet.getBoolean(ADMIN_FLAG_COLUMN));
        user.setTestsCompleted(resultSet.getInt(TESTS_COMPLETED_COLUMN));
        user.setAverageEvaluation(resultSet.getInt(AVERAGE_EVALUATION_COLUMN));
        return user;
    }

    @Override
    public User makeUnique(Map<Integer, User> map,
                           User user) {
        map.putIfAbsent(user.getId(), user);
        return map.get(user.getId());
    }
}
