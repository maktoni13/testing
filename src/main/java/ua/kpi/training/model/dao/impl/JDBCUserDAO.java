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

        try (PreparedStatement ps = connection.prepareStatement
                (DAOResourceBundle.getStatement(DAOKeyContainer.INSERT_USER))) {
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getFirstName());
            ps.setString(5, entity.getLastName());
            ps.setString(6, entity.getFirstNameUA());
            ps.setString(7, entity.getLastNameUA());
            ps.setBoolean(8, entity.isEnabled());
            ps.setBoolean(9, entity.isAdmin());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //        try (PreparedStatement preparedStatement = connection.prepareStatement(
//                DAOResourceBundle.getStatement(
//                        DAOKeyContainer.SELECT_USER_BY_USERNAME))){
//            preparedStatement.setString(1, username);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()){
//                UserMapper userMapper = new UserMapper();
//                return userMapper.extractFromResultSet(resultSet);
//            }
//            return null;
//        } catch (SQLException e){
//            throw new RuntimeException(e);
//        }
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
        return findUserByParam(DAOKeyContainer.SELECT_USER_BY_USERNAME,
                username);
    }

    @Override
    public boolean isUserWithSameUsernameExist(String username) {
        return findUserByUsername(username) != null;
    }

    @Override
    public User findUserByEmail(String email) {
        return findUserByParam(DAOKeyContainer.SELECT_USER_BY_EMAIL,
                email);
    }

    @Override
    public boolean isUserWithSameEmailExist(String email) {
        return findUserByEmail(email) != null;
    }

    private User findUserByParam(String sqlStatement, String param) {
        try (PreparedStatement preparedStatement = connection.
                prepareStatement(DAOResourceBundle.getStatement(sqlStatement))) {
            preparedStatement.setString(1, param);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UserMapper userMapper = new UserMapper();
                return userMapper.extractFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
