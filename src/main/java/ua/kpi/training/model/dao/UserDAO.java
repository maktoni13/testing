package ua.kpi.training.model.dao;

import ua.kpi.training.model.entity.User;

public interface UserDAO extends GenericDAO<User> {
    User findUserByUsername(String username);
}
