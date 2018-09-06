package ua.kpi.training.controller.command.dto;

import ua.kpi.training.model.entity.Test;
import ua.kpi.training.model.entity.Theme;

import java.util.List;

public class TestsListByThemeDTO {
    Theme theme;
    List<Test> testList;

    public TestsListByThemeDTO() {
    }

    public TestsListByThemeDTO(Theme theme, List<Test> testList) {
        this.theme = theme;
        this.testList = testList;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }
}
