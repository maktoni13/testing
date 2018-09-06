package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.TestDAO;
import ua.kpi.training.model.dao.mapper.TestMapper;
import ua.kpi.training.model.dao.mapper.ThemeMapper;
import ua.kpi.training.model.dao.resource.DAOKeyContainer;
import ua.kpi.training.model.dao.resource.DAOResourceBundle;
import ua.kpi.training.model.entity.Test;
import ua.kpi.training.model.entity.Theme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTestDAO implements TestDAO {
    private Connection connection;

    @Override
    public void create(Test entity) {

    }

    @Override
    public Test findById(int id) {
        return null;
    }

    @Override
    public List<Test> findAll() {
        return null;
    }

    @Override
    public void update(Test entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    @Override
    public List<Test> findAllByThemeId(int themeId) {
        List<Test> testList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.
                prepareStatement(
                        DAOResourceBundle.getStatement(
                                DAOKeyContainer.SELECT_TESTS_BY_THEME_ID))) {
            preparedStatement.setInt(1, themeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            TestMapper testMapper = new TestMapper();
            while (resultSet.next()) {
                testList.add(testMapper.extractFromResultSet(resultSet));
            }
            return testList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
