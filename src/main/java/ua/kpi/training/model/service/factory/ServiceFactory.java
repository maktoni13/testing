package ua.kpi.training.model.service.factory;

import ua.kpi.training.model.dao.DAOFactory;
import ua.kpi.training.model.service.LoginService;
import ua.kpi.training.model.service.RegistrationService;
import ua.kpi.training.model.service.StudentService;
import ua.kpi.training.model.service.impl.LoginServiceImpl;
import ua.kpi.training.model.service.impl.RegistrationServiceImpl;
import ua.kpi.training.model.service.impl.StudentServiceImpl;

public class ServiceFactory {
    private static volatile ServiceFactory serviceFactoryInstance;
    private DAOFactory daoFactory;

    private ServiceFactory() {
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        return new LoginServiceImpl(daoFactory.createUserDAO());
    }
    public RegistrationService getRegistrationService(){
        return new RegistrationServiceImpl();
    }
    public StudentService getStudentService(){
        return new StudentServiceImpl();
    }

}
