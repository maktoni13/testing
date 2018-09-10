package ua.kpi.training.model.dao.mapper;

import ua.kpi.training.model.entity.Test;
import ua.kpi.training.model.entity.Theme;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TestMapper implements ObjectMapper<Test> {
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String NAME_UA_COLUMN = "name_ua";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String DESCRIPTION_UA_COLUMN = "description_ua";
    private static final String THEME_ID_COLUMN = "theme_id";
    private static final String TEST_INACTIVE_COLUMN = "inactive";

    @Override
    public Test extractFromResultSet(ResultSet resultSet) throws SQLException {
        Test test = new Test();
        test.setId(resultSet.getInt(ID_COLUMN));
        test.setName(resultSet.getString(NAME_COLUMN));
        test.setNameUA(resultSet.getString(NAME_UA_COLUMN));
        test.setDescription(resultSet.getString(DESCRIPTION_COLUMN));
        test.setDescriptionUA(resultSet.getString(DESCRIPTION_UA_COLUMN));
        test.setThemeId(resultSet.getInt(THEME_ID_COLUMN));
        test.setInactive(resultSet.getBoolean(TEST_INACTIVE_COLUMN));
        return test;
    }

    @Override
    public Test makeUnique(Map<Integer, Test> map, Test objectT) {
        return null;
    }
}
