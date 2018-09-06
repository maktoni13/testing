package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.ThemeDAO;
import ua.kpi.training.model.dao.mapper.ThemeMapper;
import ua.kpi.training.model.dao.resource.DAOKeyContainer;
import ua.kpi.training.model.dao.resource.DAOResourceBundle;
import ua.kpi.training.model.entity.Theme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCThemeDAO implements ThemeDAO {
    private Connection connection;

    public JDBCThemeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Theme entity) {

    }

    @Override
    public Theme findById(int id) {
        Theme theme = new Theme();
        try (PreparedStatement preparedStatement = connection.
                prepareStatement(
                        DAOResourceBundle.getStatement(
                                DAOKeyContainer.SELECT_THEME_BY_ID))) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ThemeMapper themeMapper = new ThemeMapper();
            if (resultSet.next()) {
                theme = themeMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return theme;
    }

    @Override
    public List<Theme> findAll() {
        List<Theme> themeList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.
                prepareStatement(
                        DAOResourceBundle.getStatement(
                                DAOKeyContainer.SELECT_ALL_THEMES))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            ThemeMapper themeMapper = new ThemeMapper();
            while (resultSet.next()) {
                themeList.add(themeMapper.extractFromResultSet(resultSet));
            }
            return themeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Theme entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
