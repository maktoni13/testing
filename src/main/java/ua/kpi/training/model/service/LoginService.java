package ua.kpi.training.model.service;

import ua.kpi.training.controller.command.dto.UserDTO;

public interface LoginService{
    UserDTO getUserDTOUsernamePassword(String username, String password);
}
