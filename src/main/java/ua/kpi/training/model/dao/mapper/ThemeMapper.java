package ua.kpi.training.model.dao.mapper;

import ua.kpi.training.model.entity.Theme;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Class Theme mapper
 * <p> Mapper for object Theme from theme database table
 *
 * @author Anton Makukhin
 */
public class ThemeMapper implements ObjectMapper<Theme>{

    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String NAME_UA_COLUMN = "name_ua";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String DESCRIPTION_UA_COLUMN = "description_ua";

    @Override
    public Theme extractFromResultSet(ResultSet resultSet) throws SQLException {
        Theme theme = new Theme();
        theme.setId(resultSet.getInt(ID_COLUMN));
        theme.setName(resultSet.getString(NAME_COLUMN));
        theme.setNameUA(resultSet.getString(NAME_UA_COLUMN));
        theme.setDescription(resultSet.getString(DESCRIPTION_COLUMN));
        theme.setDescriptionUA(resultSet.getString(DESCRIPTION_UA_COLUMN));
        return theme;
    }

    @Override
    public Theme makeUnique(Map<Integer, Theme> map, Theme objectT) {
        map.putIfAbsent(objectT.getId(), objectT);
        return map.get(objectT.getId());
    }
}
