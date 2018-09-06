package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.QuestionDAO;
import ua.kpi.training.model.entity.Question;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCQuestionDAO implements QuestionDAO {
    private Connection connection;

    public JDBCQuestionDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Question entity) {

    }

    @Override
    public Question findById(int id) {
        return null;
    }

    @Override
    public List<Question> findAll() {
        return null;
    }

    @Override
    public void update(Question entity) {

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
