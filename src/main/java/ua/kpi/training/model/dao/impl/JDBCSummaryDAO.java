package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.SummaryDAO;
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
    public void create(Summary entity) {

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
    public void update(Summary entity) {

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
