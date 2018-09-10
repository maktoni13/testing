package ua.kpi.training.model.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.logger.LoggerMessages;
import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.dao.resource.DAOBundle;
import ua.kpi.training.model.dao.resource.DAOKey;
import ua.kpi.training.view.resource.MessageBundle;
import ua.kpi.training.view.resource.MessageKey;

import java.sql.*;
import java.util.List;

public abstract class JDBCAbstractDAO<T> {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(JDBCAbstractDAO.class);

    public abstract void fillDeletePrepareStatement(PreparedStatement ps, T entity) throws SQLException;
    public abstract void fillInsertPrepareStatement(PreparedStatement ps, T entity) throws SQLException;
    public abstract void fillUpdatePrepareStatement(PreparedStatement ps, T entity) throws SQLException;

    public abstract void setKeyToEntity(T entity, ResultSet rs) throws SQLException;


    boolean createEntity(T entity, String sql, Connection connection) throws DAOException{

        try (PreparedStatement ps = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            fillInsertPrepareStatement(ps, entity);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()){
                setKeyToEntity(entity, rs);
            } else {
                throw new DAOException(DAOException.DAO_EXCEPTION);
            }
        } catch (SQLException e) {
            throw new DAOException(DAOException.DAO_EXCEPTION, e);
        }
        return true;

    }

    boolean updateEntity(T entity, String sql, Connection connection) throws DAOException{

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            fillUpdatePrepareStatement(ps, entity);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER_SLF4J.error(LoggerMessages.ERROR_DAO_UPDATE_QUERY, e);
            throw new DAOException(DAOException.DAO_EXCEPTION, e);
        }
        return true;

    }

    boolean deleteEntityList(List<T> entityList, String sql, Connection connection) throws DAOException{
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            for (T entity : entityList) {
                fillDeletePrepareStatement(ps, entity);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            LOGGER_SLF4J.error(LoggerMessages.ERROR_DAO_DELETE_QUERY, e);
            throw new DAOException(
                    MessageBundle.getMessage(MessageKey.SQL_ERROR), e);
        }
        return true;
    }

    boolean createEntityList(List<T> entityList, String sql, Connection connection) throws DAOException{
        if (entityList == null) {
            return true;
        }
        try (PreparedStatement ps = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS )) {
            for (T entity : entityList) {
                fillInsertPrepareStatement(ps, entity);
                ps.addBatch();
            }
            ps.executeBatch();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs == null){
                throw new DAOException(DAOException.DAO_EXCEPTION);
            }
            for (T entity : entityList) {
                if (rs.next()){
                    setKeyToEntity(entity, rs);
                } else {
                    throw new DAOException(DAOException.DAO_EXCEPTION);
                }
            }
        } catch (SQLException e) {
            LOGGER_SLF4J.error(LoggerMessages.ERROR_DAO_INSERT_QUERY);
            throw new DAOException(
                    MessageBundle.getMessage(MessageKey.SQL_ERROR), e);
        }
        return true;
    }

}
