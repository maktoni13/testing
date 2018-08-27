package ua.kpi.training.model.dao;

import ua.kpi.training.model.dao.factory.JDBCDAOFactory;

public abstract class DAOFactory {

    private static volatile DAOFactory daoFactoryInstance;

    public abstract UserDAO createUserDAO();

    public static DAOFactory getInstance(){
        if( daoFactoryInstance == null ){
            synchronized (DAOFactory.class){
                if(daoFactoryInstance == null){
                    DAOFactory factory = new JDBCDAOFactory();
                    daoFactoryInstance = factory;
                }
            }
        }
        return daoFactoryInstance;
    }


}
