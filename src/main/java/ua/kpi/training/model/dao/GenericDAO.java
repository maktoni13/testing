package ua.kpi.training.model.dao;

import ua.kpi.training.model.dao.exception.DAOException;

import java.util.List;

public interface GenericDAO<T> extends AutoCloseable {
    void create(T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
}
