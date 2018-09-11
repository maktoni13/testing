package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.SummaryDAO;
import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.dao.factory.JDBCDAOFactory;
import ua.kpi.training.model.dao.mapper.SummaryMapper;
import ua.kpi.training.model.dao.resource.DAOBundle;
import ua.kpi.training.model.dao.resource.DAOKey;
import ua.kpi.training.model.entity.Summary;

import java.sql.*;
import java.util.List;

public class JDBCSummaryDAO extends JDBCAbstractDAO<Summary> implements SummaryDAO {
    private Connection connection;

    public JDBCSummaryDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Summary findById(int id) {
        Summary summary = new Summary();
        try (PreparedStatement ps = this.connection.
                prepareStatement(
                        DAOBundle.getStatement(
                                DAOKey.SELECT_SUMMARY_BY_ID))) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            SummaryMapper summaryMapper = new SummaryMapper();
            if (resultSet.next()) {
                summary = summaryMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return summary;
    }

    @Override
    public List<Summary> findAll() {
        return null;
    }

    @Override
    public boolean create(Summary entity) throws DAOException {
        return createEntity(entity,
                DAOBundle.getStatement(DAOKey.INSERT_SUMMARY),
                connection);
    }

    @Override
    public boolean update(Summary entity) throws DAOException {
        return updateEntity(entity,
                DAOBundle.getStatement(DAOKey.UPDATE_SUMMARY),
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
    public void fillDeletePrepareStatement(PreparedStatement ps, Summary entity) throws SQLException {

    }

    @Override
    public void fillInsertPrepareStatement(PreparedStatement ps, Summary entity) throws SQLException {
        ps.setInt(1, entity.getTest().getId());
        ps.setInt(2, entity.getUser().getId());
        ps.setBoolean(3, entity.isInformed());
        ps.setDate(4, entity.getStartSqlDate());
        ps.setDate(5, entity.getFinishSqlDate());
        ps.setInt(6, entity.getQuestionsQuantity());
        ps.setInt(7, entity.getCorrectAnswered());
        ps.setBoolean(8, entity.isBestResult());
    }

    @Override
    public void fillUpdatePrepareStatement(PreparedStatement ps, Summary entity) throws SQLException {

    }

    @Override
    public void setKeyToEntity(Summary entity, ResultSet rs) throws SQLException {
        entity.setId(rs.getInt(1));
    }
}
