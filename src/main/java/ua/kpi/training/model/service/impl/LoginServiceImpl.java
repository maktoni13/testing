package ua.kpi.training.model.service.impl;

import ua.kpi.training.controller.command.dto.UserDTO;
import ua.kpi.training.controller.command.utility.SCryptPassHashing;
import ua.kpi.training.model.dao.UserDAO;
import ua.kpi.training.model.dao.DAOFactory;
import ua.kpi.training.model.entity.User;
import ua.kpi.training.model.service.LoginService;

public class LoginServiceImpl implements LoginService {

    private DAOFactory daoFactory = DAOFactory.getInstance();

    public LoginServiceImpl() {
    }

    private boolean checkPassword(String passwordText, String passwordHash) {
        return (passwordText != null)
                && (!passwordText.equals(""))
                && SCryptPassHashing.validPassword(passwordText, passwordHash);
    }

    private UserDTO constructUserDTO(User user, String passwordText) {
        UserDTO userDTO = new UserDTO();
        if (user == null) {
            userDTO.setExists(false);
        } else {
            userDTO.setExists(true);
            userDTO.setUsername(user.getUsername());
            userDTO.setEnabled(user.isEnabled());
            userDTO.setAuthority(user.getAuthority());
            userDTO.setValidPassword(
                    checkPassword(passwordText, user.getPassword()));
        }
        return userDTO;
    }

    @Override
    public UserDTO getUserDTOUsernamePassword(String username, String password) {
        UserDAO userDAO = daoFactory.createUserDAO();
        return constructUserDTO(userDAO.findUserByUsername(username), password);
    }


}
