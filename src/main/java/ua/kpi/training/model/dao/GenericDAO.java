package ua.kpi.training.model.dao;

import ua.kpi.training.model.dao.exception.DAOException;

import java.util.List;

public interface GenericDAO<T> extends AutoCloseable{
    boolean create(T entity) throws DAOException;
    T findById(int id) throws DAOException;
    List<T> findAll() throws DAOException;
    boolean update(T entity) throws DAOException;
    boolean delete(int id) throws DAOException;
    void close() throws Exception;
}
