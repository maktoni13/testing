package ua.kpi.training.model.dao.mapper;

import ua.kpi.training.model.entity.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TestMapper implements ObjectMapper<Test> {
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String DESCRIPTION_UA_COLUMN = "description_ua";

    @Override
    public Test extractFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public Test makeUnique(Map<Integer, Test> map, Test objectT) {
        return null;
    }
}
