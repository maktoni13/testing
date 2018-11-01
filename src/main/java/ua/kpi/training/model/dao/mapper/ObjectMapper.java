package ua.kpi.training.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Interface Object Mapper
 * <p> Common Mapper Interface for realisation
 *
 * @author Anton Makukhin
 */
public interface ObjectMapper<T> {
    T extractFromResultSet(ResultSet resultSet) throws SQLException;
    T makeUnique(Map<Integer, T> map, T objectT);
}