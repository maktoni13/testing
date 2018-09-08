package ua.kpi.training.model.dao;

import ua.kpi.training.model.dao.factory.JDBCDAOFactory;

import java.sql.Connection;

public abstract class DAOFactory {

    private static volatile DAOFactory daoFactoryInstance;

    public abstract UserDAO createUserDAO();
    public abstract ThemeDAO createThemeDAO();
    public abstract TestDAO createTestDAO();
    public abstract QuestionDAO createQuestionDAO();

    public abstract Connection getConnection();

    public static DAOFactory getInstance(){
        if( daoFactoryInstance == null ){
            synchronized (DAOFactory.class){
                if(daoFactoryInstance == null){
                    daoFactoryInstance = new JDBCDAOFactory();
                }
            }
        }
        return daoFactoryInstance;
    }

}
