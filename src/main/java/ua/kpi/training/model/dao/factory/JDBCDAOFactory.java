package ua.kpi.training.model.dao.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.logger.LoggerMessages;
import ua.kpi.training.model.dao.*;
import ua.kpi.training.model.dao.connection.ConnectionPoolHolder;
import ua.kpi.training.model.dao.impl.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDAOFactory extends DAOFactory {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(JDBCDAOFactory.class);

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private Connection connection;

    @Override
    public UserDAO createUserDAO() {
        return new JDBCUserDAO(getConnection());
    }

    @Override
    public UserDAO createUserDAO(Connection connection) {
        return new JDBCUserDAO(connection);
    }

    @Override
    public ThemeDAO createThemeDAO() {
        return new JDBCThemeDAO(getConnection());
    }

    @Override
    public ThemeDAO createThemeDAO(Connection connection) {
        return new JDBCThemeDAO(connection);
    }

    @Override
    public TestDAO createTestDAO() {
        return new JDBCTestDAO(getConnection());
    }

    @Override
    public TestDAO createTestDAO(Connection connection) {
        return new JDBCTestDAO(connection);
    }

    @Override
    public QuestionDAO createQuestionDAO() {
        return new JDBCQuestionDAO(getConnection());
    }

    @Override
    public QuestionDAO createQuestionDAO(Connection connection) {
        return new JDBCQuestionDAO(connection);
    }

    @Override
    public AnswerDAO createAnswerDAO() {
        return new JDBCAnswerDAO(getConnection());
    }

    @Override
    public AnswerDAO createAnswerDAO(Connection connection) { return new JDBCAnswerDAO(connection);
    }

    @Override
    public SummaryDAO createSummaryDAO() {
        return new JDBCSummaryDAO(getConnection());
    }

    @Override
    public SummaryDAO createSummaryDAO(Connection connection) {
        return new JDBCSummaryDAO(connection);
    }

    @Override
    public QuestionResultDAO createQuestionResultDAO() {
        return new JDBCQuestionResultDAO(getConnection());
    }

    @Override
    public QuestionResultDAO createQuestionResultDAO(Connection connection) {
        return new JDBCQuestionResultDAO(connection);

    }

    @Override
    public AnswerResultDAO createAnswerResultDAO() {
        return new JDBCAnswerResultDAO(getConnection());
    }

    @Override
    public AnswerResultDAO createAnswerResultDAO(Connection connection) {
        return new JDBCAnswerResultDAO(connection);
    }

    @Override
    public Connection getConnection(){
        try {
            if (connection == null || connection.isClosed()){
                connection = dataSource.getConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // TODO: Exception
        }
        return connection;
    }

    public static void connectionClose(Connection connection){
        try {
            if (!connection.isClosed() && connection.getAutoCommit()) {
                connection.close();
            }
            if (connection.isClosed()){
                LOGGER_SLF4J.warn(LoggerMessages.WARN_UNEXPECTED_CONNECTION_CLOSING);
            }
        } catch (SQLException e) {
            LOGGER_SLF4J.error(LoggerMessages.ERROR_DAO_CONNECTION_CLOSE_SQL_EXCEPTION, e);
        }
    }



}
