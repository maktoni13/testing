package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.UserDAO;
import ua.kpi.training.model.dao.factory.JDBCDAOFactory;
import ua.kpi.training.model.dao.mapper.UserMapper;
import ua.kpi.training.model.dao.resource.DAOKey;
import ua.kpi.training.model.dao.resource.DAOBundle;
import ua.kpi.training.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDAO implements UserDAO {
    private Connection connection;

    public JDBCUserDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean create(User entity) {

        try (PreparedStatement ps = connection.prepareStatement
                (DAOBundle.getStatement(DAOKey.INSERT_USER))) {
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
            // TODO: Logging and validation result updating
            return false;
        }
        return true;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> userArrayList = new ArrayList<>();
        try (PreparedStatement ps = connection.
                prepareStatement(
                        DAOBundle.getStatement(
                                DAOKey.SELECT_ALL_USERS))) {
            ResultSet resultSet = ps.executeQuery();
            UserMapper userMapper = new UserMapper();
            while (resultSet.next()) {
                userArrayList.add(userMapper.extractFromResultSet(resultSet));
            }
            return userArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User findUserByUsername(String username) {
        return findUserByParam(DAOKey.SELECT_USER_BY_USERNAME,
                username);
    }

    @Override
    public boolean isUserWithSameUsernameExist(String username) {
        return findUserByUsername(username) != null;
    }

    @Override
    public User findUserByEmail(String email) {
        return findUserByParam(DAOKey.SELECT_USER_BY_EMAIL,
                email);
    }

    @Override
    public boolean isUserWithSameEmailExist(String email) {
        return findUserByEmail(email) != null;
    }

    private User findUserByParam(String sqlStatement, String param) {
        try (PreparedStatement ps = connection.
                prepareStatement(DAOBundle.getStatement(sqlStatement))) {
            ps.setString(1, param);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                UserMapper userMapper = new UserMapper();
                return userMapper.extractFromResultSet(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        JDBCDAOFactory.connectionClose(connection);
    }

}
