package ua.kpi.training.model.service;

public interface ILogInService {
    boolean checkUserNamePassword(String userName, String password);
}
