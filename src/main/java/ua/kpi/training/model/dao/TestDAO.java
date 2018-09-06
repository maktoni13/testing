package ua.kpi.training.model.dao;

import ua.kpi.training.controller.command.dto.TestsListByThemeDTO;
import ua.kpi.training.model.entity.Test;

import java.util.List;

public interface TestDAO extends GenericDAO<Test>{
    List<Test> findAllByThemeId(int themeId);
}
