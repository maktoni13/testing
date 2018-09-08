package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.exception.DAOException;

import java.sql.Connection;

public abstract class JDBCAbstractDAO<T> implements AutoCloseable {
    protected Connection connection;

    @Override
    public void close() throws Exception {

    }
}
