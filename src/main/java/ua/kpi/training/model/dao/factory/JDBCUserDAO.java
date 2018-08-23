package ua.kpi.training.model.dao.factory;

import ua.kpi.training.model.dao.IUserDAO;
import ua.kpi.training.model.entity.User;

import java.util.List;

public class JDBCUserDAO implements IUserDAO {
    @Override
    public void create(User entity) {

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
