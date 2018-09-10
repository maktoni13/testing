package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.SummaryDAO;
import ua.kpi.training.model.dao.factory.JDBCDAOFactory;
import ua.kpi.training.model.entity.Summary;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCSummaryDAO implements SummaryDAO {
    private Connection connection;

    public JDBCSummaryDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Summary findById(int id) {
        return null;
    }

    @Override
    public List<Summary> findAll() {
        return null;
    }

    @Override
    public boolean create(Summary entity) {
        return false;
    }

    @Override
    public boolean update(Summary entity) {
        return false;
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
