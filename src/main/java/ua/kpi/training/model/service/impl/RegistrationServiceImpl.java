package ua.kpi.training.model.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.controller.command.dto.RegistrationUserDTO;
import ua.kpi.training.logger.LoggerMessages;
import ua.kpi.training.model.dao.DAOFactory;
import ua.kpi.training.model.dao.UserDAO;
import ua.kpi.training.model.dao.exception.NonUniqueUserException;
import ua.kpi.training.model.entity.User;
import ua.kpi.training.model.service.RegistrationService;
import ua.kpi.training.view.resource.MessageBundle;
import ua.kpi.training.view.resource.MessageKey;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistrationServiceImpl implements RegistrationService {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    private DAOFactory daoFactory = DAOFactory.getInstance();

    public RegistrationServiceImpl() {
    }

    private User getUserFromRegistrationDTO(RegistrationUserDTO regUserDTO) {
        User user = new User();
        user.setAdmin(false);
        user.setEnabled(false);
        user.setUsername(regUserDTO.getUsername());
        user.setPassword(regUserDTO.getPassword());
        user.setEmail(regUserDTO.getEmail());
        user.setLastName(regUserDTO.getLastName());
        user.setLastNameUA(regUserDTO.getLastNameUA());
        user.setFirstName(regUserDTO.getFirstName());
        user.setFirstNameUA(regUserDTO.getFirstNameUA());
        return user;
    }

    @Override
    public boolean registerUser(RegistrationUserDTO regUserDTO) throws NonUniqueUserException {

        User user = getUserFromRegistrationDTO(regUserDTO);
        StringBuilder message = new StringBuilder();
        Connection connection = daoFactory.getConnection();
        UserDAO userDAO = daoFactory.createUserDAO(connection);
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        } catch (SQLException e) {
            LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, e);
            regUserDTO.appendValidationResult(MessageBundle.getMessage(MessageKey.SQL_ERROR));
            return false;
        }

        boolean exceptionFlag = false;
        if (userDAO.isUserWithSameUsernameExist(user.getUsername())) {
            message.append(MessageBundle.getMessage(MessageKey.USERNAME_ALREADY_REGISTERED));
            message.append("\n");
            message.append(user.getUsername());
            exceptionFlag = true;
        }
        if (userDAO.isUserWithSameEmailExist(user.getEmail())) {
            message.append(MessageBundle.getMessage(MessageKey.EMAIL_ALREADY_REGISTERED));
            message.append("\n");
            message.append(user.getEmail());
            exceptionFlag = true;
        }

        if (exceptionFlag) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException eClose) {
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, eClose);
            }
            throw new NonUniqueUserException(message.toString());
        }

        try {
            userDAO.create(user);
            connection.commit();
        } catch (Exception e) {
            LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE, e);
            try {
                connection.rollback();
            } catch (SQLException eRollback) {
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, eRollback);
            }
            regUserDTO.appendValidationResult(MessageBundle.getMessage(MessageKey.SQL_ERROR));
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException eClose) {
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, eClose);
            }
        }

        return true;
    }
}
