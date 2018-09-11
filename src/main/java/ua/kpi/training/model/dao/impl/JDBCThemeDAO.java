package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.ThemeDAO;
import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.dao.factory.JDBCDAOFactory;
import ua.kpi.training.model.dao.mapper.ThemeMapper;
import ua.kpi.training.model.dao.resource.DAOKey;
import ua.kpi.training.model.dao.resource.DAOBundle;
import ua.kpi.training.model.entity.Theme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class JDBC Theme DAO
 * <p> implementation of DAO for theme database table
 *
 * @author Anton Makukhin
 */
public class JDBCThemeDAO implements ThemeDAO {
    private Connection connection;

    public JDBCThemeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Theme entity) throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement
                (DAOBundle.getStatement(DAOKey.INSERT_THEME))) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getNameUA());
            ps.setString(3, entity.getDescription());
            ps.setString(4, entity.getDescriptionUA());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(DAOException.DAO_EXCEPTION, e);
        }
    }

    @Override
    public Theme findById(int id) throws DAOException {
        Theme theme = new Theme();
        try (PreparedStatement ps = connection.
                prepareStatement(
                        DAOBundle.getStatement(
                                DAOKey.SELECT_THEME_BY_ID))) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            ThemeMapper themeMapper = new ThemeMapper();
            if (resultSet.next()) {
                theme = themeMapper.extractFromResultSet(resultSet);
            }
            return theme;
        } catch (SQLException e) {
            throw new DAOException(DAOException.DAO_EXCEPTION, e);
        }
    }

    @Override
    public List<Theme> findAll() {
        List<Theme> themeList = new ArrayList<>();
        try (PreparedStatement ps = connection.
                prepareStatement(
                        DAOBundle.getStatement(
                                DAOKey.SELECT_ALL_THEMES))) {
            ResultSet resultSet = ps.executeQuery();
            ThemeMapper themeMapper = new ThemeMapper();
            while (resultSet.next()) {
                themeList.add(themeMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return themeList;
    }

    @Override
    public boolean update(Theme entity) throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement
                (DAOBundle.getStatement(DAOKey.UPDATE_THEME))) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getNameUA());
            ps.setString(3, entity.getDescription());
            ps.setString(4, entity.getDescriptionUA());
            ps.setInt(5, entity.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(DAOException.DAO_EXCEPTION, e);
        }
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void close() {
        JDBCDAOFactory.connectionClose(connection);
    }

}
