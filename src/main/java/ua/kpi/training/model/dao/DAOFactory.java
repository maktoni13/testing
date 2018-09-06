package ua.kpi.training.model.dao;

import ua.kpi.training.model.dao.factory.JDBCDAOFactory;

public abstract class DAOFactory {

    private static volatile DAOFactory daoFactoryInstance;

    public abstract UserDAO createUserDAO();
    public abstract ThemeDAO createThemeDAO();
    public abstract TestDAO createTestDAO();

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
