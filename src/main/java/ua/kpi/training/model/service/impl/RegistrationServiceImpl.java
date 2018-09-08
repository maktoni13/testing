package ua.kpi.training.model.service.impl;

import ua.kpi.training.controller.command.dto.RegistrationUserDTO;
import ua.kpi.training.model.dao.DAOFactory;
import ua.kpi.training.model.dao.UserDAO;
import ua.kpi.training.model.entity.User;
import ua.kpi.training.model.dao.exception.NonUniqueUserException;
import ua.kpi.training.model.service.RegistrationService;
import ua.kpi.training.view.resource.MessageBundle;
import ua.kpi.training.view.resource.MessageKey;

import java.sql.SQLException;

public class RegistrationServiceImpl extends AbstractService<RegistrationUserDTO, Boolean> implements RegistrationService{

    public RegistrationServiceImpl() {
    }

    private User getUserFromRegistrationDTO(RegistrationUserDTO regUserDTO){
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
    public boolean registerUser(RegistrationUserDTO regUserDTO){// throws NonUniqueUserException {

        try{
            return performServiceInSerializableTransaction(regUserDTO);
        } catch (SQLException e){
            return false;
        }

    }

    @Override
    public Boolean performService(RegistrationUserDTO regUserDTO){// throws NonUniqueUserException{

        User user = getUserFromRegistrationDTO(regUserDTO);
        UserDAO userDAO = daoFactory.createUserDAO();
        StringBuilder message = new StringBuilder();
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
            throw new RuntimeException(message.toString());
            //throw new NonUniqueUserException(message.toString());
        }

        try {
            userDAO.create(user);
        } catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
