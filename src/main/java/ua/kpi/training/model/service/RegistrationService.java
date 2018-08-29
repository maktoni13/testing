package ua.kpi.training.model.service;

import ua.kpi.training.controller.command.dto.RegistrationUserDTO;
import ua.kpi.training.model.entity.exception.NonUniqueUserException;

public interface RegistrationService extends CommonService {
    boolean registerUser(RegistrationUserDTO regUserDTO) throws NonUniqueUserException;
}
