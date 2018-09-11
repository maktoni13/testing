package ua.kpi.training.model.service;

import ua.kpi.training.controller.command.dto.TestsListByThemeDTO;
import ua.kpi.training.model.entity.Test;

public interface TestService {
    TestsListByThemeDTO getTestsListByThemeId(int id);
    boolean saveTestEntity(Test test);
    Test getTestEntity(int id);
    int prepareSummaryForTestPassing(int id, String username);
}
