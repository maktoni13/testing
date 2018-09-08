package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.TestDAO;
import ua.kpi.training.model.dao.mapper.ObjectMapper;
import ua.kpi.training.model.dao.mapper.TestMapper;
import ua.kpi.training.model.dao.resource.DAOKeyContainer;
import ua.kpi.training.model.dao.resource.DAOResourceBundle;
import ua.kpi.training.model.entity.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTestDAO extends JDBCAbstractDAO<Test> implements TestDAO {

    public JDBCTestDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Test entity) {

    }

    @Override
    public Test findById(int id) {
        Test test = new Test();
        try (PreparedStatement preparedStatement = this.connection.
                prepareStatement(
                        DAOResourceBundle.getStatement(
                                DAOKeyContainer.SELECT_TEST_BY_ID))) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            TestMapper themeMapper = new TestMapper();
            if (resultSet.next()) {
                test = themeMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return test;
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
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
            ObjectMapper<Test> testMapper = new TestMapper();
            while (resultSet.next()) {
                testList.add(testMapper.extractFromResultSet(resultSet));
            }
            return testList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
