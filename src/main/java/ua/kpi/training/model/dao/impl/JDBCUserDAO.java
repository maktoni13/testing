package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.UserDAO;
import ua.kpi.training.model.dao.mapper.UserMapper;
import ua.kpi.training.model.dao.resource.DAOKeyContainer;
import ua.kpi.training.model.dao.resource.DAOResourceBundle;
import ua.kpi.training.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCUserDAO implements UserDAO {
    private Connection connection;

    public JDBCUserDAO(Connection connection) {
        this.connection = connection;
    }




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

    @Override
    public User findUserByUsername(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                DAOResourceBundle.getStatement(
                        DAOKeyContainer.SELECT_USER_BY_USERNAME))){
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                UserMapper userMapper = new UserMapper();
                return userMapper.extractFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
