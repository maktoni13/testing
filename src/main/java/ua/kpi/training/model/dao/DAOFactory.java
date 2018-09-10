package ua.kpi.training.model.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.logger.LoggerMessages;
import ua.kpi.training.model.dao.factory.JDBCDAOFactory;

import java.sql.Connection;

/**
 * Abstract class for DAO Factory
 * Using for creating of DAO objects
 * @author Anton Makukhin
 */
public abstract class DAOFactory {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(DAOFactory.class);
    private static volatile DAOFactory daoFactoryInstance;

    public abstract Connection getConnection();
    public abstract UserDAO createUserDAO();
    public abstract UserDAO createUserDAO(Connection connection);
    public abstract ThemeDAO createThemeDAO();
    public abstract ThemeDAO createThemeDAO(Connection connection);
    public abstract TestDAO createTestDAO();
    public abstract TestDAO createTestDAO(Connection connection);
    public abstract QuestionDAO createQuestionDAO();
    public abstract QuestionDAO createQuestionDAO(Connection connection);
    public abstract AnswerDAO createAnswerDAO();
    public abstract AnswerDAO createAnswerDAO(Connection connection);

    /**
     * DAO Factory Instance getting
     * @return DAO Factory instance
     */
    public static DAOFactory getInstance(){
        if( daoFactoryInstance == null ){
            synchronized (DAOFactory.class){
                if(daoFactoryInstance == null){
                    daoFactoryInstance = new JDBCDAOFactory();
                    LOGGER_SLF4J.info(LoggerMessages.INFO_DAO_FACTORY_CREATED);
                }
            }
        }
        return daoFactoryInstance;
    }

}
