package ua.kpi.training.model.dao.mapper;

import ua.kpi.training.model.entity.Theme;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ThemeMapper implements ObjectMapper<Theme>{
    private int id;
    private String description;
    private String descriptionUA;

    private static final String ID_COLUMN = "id";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String DESCRIPTION_UA_COLUMN = "description_ua";

    @Override
    public Theme extractFromResultSet(ResultSet resultSet) throws SQLException {
        Theme theme = new Theme();
        theme.setId(resultSet.getInt(ID_COLUMN));
        theme.setDescription(resultSet.getString(DESCRIPTION_COLUMN));
        theme.setDescriptionUA(resultSet.getString(DESCRIPTION_UA_COLUMN));
        return theme;
    }

    @Override
    public Theme makeUnique(Map<Integer, Theme> map, Theme objectT) {
        return null;
    }
}
