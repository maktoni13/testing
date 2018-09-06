package ua.kpi.training.model.service;

import ua.kpi.training.model.entity.Test;

import java.util.List;

public interface TestService {
    List<Test> getTestsListByThemeId(int id);
}
