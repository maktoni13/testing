package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.AnswerDAO;
import ua.kpi.training.model.entity.Answer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCAnswerDAO implements AnswerDAO {
    private Connection connection;

    public JDBCAnswerDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Answer entity) {

    }

    @Override
    public Answer findById(int id) {
        return null;
    }

    @Override
    public List<Answer> findAll() {
        return null;
    }

    @Override
    public void update(Answer entity) {

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
