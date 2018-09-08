package ua.kpi.training.model.service;

import ua.kpi.training.controller.command.dto.RegistrationUserDTO;
import ua.kpi.training.model.dao.exception.NonUniqueUserException;

public interface RegistrationService{
    boolean registerUser(RegistrationUserDTO regUserDTO) throws NonUniqueUserException;
}
