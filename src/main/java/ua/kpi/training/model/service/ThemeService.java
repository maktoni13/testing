package ua.kpi.training.model.service;

import ua.kpi.training.model.entity.Theme;

import java.util.List;

public interface ThemeService{
    List<Theme> getAllThemes();
    Theme getThemeEntity(int id);
    boolean saveThemeEntity(Theme theme);
}
