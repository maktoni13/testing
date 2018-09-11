package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.TestDAO;
import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.dao.factory.JDBCDAOFactory;
import ua.kpi.training.model.dao.mapper.ObjectMapper;
import ua.kpi.training.model.dao.mapper.TestMapper;
import ua.kpi.training.model.dao.resource.DAOKey;
import ua.kpi.training.model.dao.resource.DAOBundle;
import ua.kpi.training.model.entity.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTestDAO extends JDBCAbstractDAO<Test> implements TestDAO {
    private Connection connection;

    public JDBCTestDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Test findById(int id) {
        Test test = new Test();
        try (PreparedStatement ps = this.connection.
                prepareStatement(
                        DAOBundle.getStatement(
                                DAOKey.SELECT_TEST_BY_ID))) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            TestMapper testMapper = new TestMapper();
            if (resultSet.next()) {
                test = testMapper.extractFromResultSet(resultSet);
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
    public List<Test> findAllByThemeId(int themeId) {
        List<Test> testList = new ArrayList<>();
        try (PreparedStatement ps = connection.
                prepareStatement(
                        DAOBundle.getStatement(
                                DAOKey.SELECT_TESTS_BY_THEME_ID))) {
            ps.setInt(1, themeId);
            ResultSet resultSet = ps.executeQuery();
            ObjectMapper<Test> testMapper = new TestMapper();
            while (resultSet.next()) {
                testList.add(testMapper.extractFromResultSet(resultSet));
            }
            return testList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean create(Test entity) throws DAOException{
        return createEntity(entity,
                DAOBundle.getStatement(DAOKey.INSERT_TEST),
                connection);
    }

    @Override
    public boolean update(Test entity) throws DAOException{
        return updateEntity(entity,
                DAOBundle.getStatement(DAOKey.UPDATE_TEST),
                connection);
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void close() {
        JDBCDAOFactory.connectionClose(connection);
    }

    @Override
    public void fillDeletePrepareStatement(PreparedStatement ps, Test entity) throws SQLException {

    }

    @Override
    public void fillInsertPrepareStatement(PreparedStatement ps, Test entity) throws SQLException {
        ps.setString(1, entity.getDescription());
        ps.setString(2, entity.getDescriptionUA());
        ps.setInt(3, entity.getTheme().getId());
        ps.setBoolean(4, entity.isInactive());
        ps.setString(5, entity.getName());
        ps.setString(6, entity.getNameUA());
    }

    @Override
    public void setKeyToEntity(Test entity, ResultSet rs) throws SQLException {
        entity.setId(rs.getInt(1));
    }

    @Override
    public void fillUpdatePrepareStatement(PreparedStatement ps, Test entity) throws SQLException {
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getNameUA());
        ps.setString(3, entity.getDescription());
        ps.setString(4, entity.getDescriptionUA());
        ps.setBoolean(5, entity.isInactive());
        ps.setInt(6, entity.getId());
    }
}
