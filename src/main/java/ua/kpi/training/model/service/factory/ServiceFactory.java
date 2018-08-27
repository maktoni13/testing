package ua.kpi.training.model.service.factory;

import ua.kpi.training.model.dao.DAOFactory;
import ua.kpi.training.model.service.LoginService;
import ua.kpi.training.model.service.impl.LoginServiceImpl;

public class ServiceFactory {
    private static volatile ServiceFactory serviceFactoryInstance;
    private DAOFactory daoFactory;

    private ServiceFactory() { // TODO: throws exception (own)
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            // TODO: throws exception (own)
        }
    }

    public static ServiceFactory getInstance(){
        if( serviceFactoryInstance == null ){
            synchronized (ServiceFactory.class){
                if(serviceFactoryInstance == null){
                    serviceFactoryInstance = new ServiceFactory();
                }
            }
        }
        return serviceFactoryInstance;
    }

    public LoginService getLoginService(){
        return new LoginServiceImpl();
    }

}
