package ua.kpi.training.model.dao;

import ua.kpi.training.model.entity.User;

public interface UserDAO extends GenericDAO<User> {
    User findUserByUsername(String username);
    boolean isUserWithSameUsernameExist(String username);
    User findUserByEmail(String email);
    boolean isUserWithSameEmailExist(String email);
}
