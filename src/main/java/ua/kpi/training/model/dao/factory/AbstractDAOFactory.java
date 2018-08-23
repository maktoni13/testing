package ua.kpi.training.model.dao.factory;

import ua.kpi.training.model.dao.IUserDAO;

public abstract class AbstractDAOFactory {

    private static volatile AbstractDAOFactory instance;

    public abstract IUserDAO createUserDAO();

    public static AbstractDAOFactory getInstance(){
        if( instance == null ){
            synchronized (AbstractDAOFactory.class){
                if(instance == null){
                    AbstractDAOFactory factory = new JDBCDAOFactory();
                    instance = factory;
                }
            }
        }
        return instance;
    }


}
