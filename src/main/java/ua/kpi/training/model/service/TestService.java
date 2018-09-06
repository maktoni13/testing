package ua.kpi.training.model.service;

import ua.kpi.training.controller.command.dto.TestsListByThemeDTO;

public interface TestService {
    TestsListByThemeDTO getTestsListByThemeId(int id);
}
