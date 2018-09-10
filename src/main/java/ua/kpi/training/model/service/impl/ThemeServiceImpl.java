package ua.kpi.training.model.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.logger.LoggerMessages;
import ua.kpi.training.model.dao.DAOFactory;
import ua.kpi.training.model.dao.ThemeDAO;
import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.entity.Theme;
import ua.kpi.training.model.service.ThemeService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThemeServiceImpl implements ThemeService {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(ThemeServiceImpl.class);
    private DAOFactory daoFactory = DAOFactory.getInstance();

    @Override
    public List<Theme> getAllThemes() {
        ThemeDAO themeDAO = daoFactory.createThemeDAO();
        try {
            return themeDAO.findAll();
        } catch (DAOException e){
            LOGGER_SLF4J.error(DAOException.DAO_EXCEPTION, e);
            return new ArrayList<>();
        }
    }

    @Override
    public Theme getThemeEntity(int id) {
        Theme theme = null;
        Connection connection = daoFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            ThemeDAO themeDAO = daoFactory.createThemeDAO(connection);
            theme = themeDAO.findById(id);;
            connection.commit();
        } catch (Exception e) {
            LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE, e);
            try {
                connection.rollback();
            } catch (SQLException eRollback){
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, eRollback);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException eClose) {
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, eClose);
            }
        }
        return theme;
    }

    @Override
    public boolean saveThemeEntity(Theme theme) {
        Connection connection = daoFactory.getConnection();
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            ThemeDAO themeDAO = daoFactory.createThemeDAO(connection);
            result = (theme.getId() > 0) ? themeDAO.update(theme) : themeDAO.create(theme);
            connection.commit();
        } catch (Exception e) {
            result = false;
            LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE, e);
            try {
                connection.rollback();
            } catch (SQLException eRollback){
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, eRollback);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException eClose) {
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, eClose);
            }
        }
        return result;
    }
}
