package ua.kpi.training.model.service.impl;

import ua.kpi.training.model.dao.IUserDAO;
import ua.kpi.training.model.dao.factory.AbstractDAOFactory;
import ua.kpi.training.model.service.ILogInService;

public class LogInServiceImpl implements ILogInService {

    AbstractDAOFactory daoFactory = AbstractDAOFactory.getInstance();

    @Override
    public boolean checkUserNamePassword(String userName, String password) {


        return false;
    }
}
