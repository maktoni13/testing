package ua.kpi.training.model.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.logger.LoggerMessages;
import ua.kpi.training.model.dao.AnswerDAO;
import ua.kpi.training.model.dao.PassedTestDAO;
import ua.kpi.training.model.dao.factory.JDBCDAOFactory;
import ua.kpi.training.model.entity.Answer;
import ua.kpi.training.model.entity.PassedTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCPassedTestDAO implements PassedTestDAO {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(JDBCPassedTestDAO.class);
    private Connection connection;

    public JDBCPassedTestDAO() {
    }

    public JDBCPassedTestDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public PassedTest findById(int id) {
        return null;
    }

    @Override
    public List<PassedTest> findAll() {
        return null;
    }

    @Override
    public boolean create(PassedTest entity) {
        return false;
    }

    @Override
    public boolean update(PassedTest entity) {
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

