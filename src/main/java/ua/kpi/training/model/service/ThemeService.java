package ua.kpi.training.model.service;

import ua.kpi.training.model.entity.Theme;

import java.util.List;

public interface ThemeService extends CommonService {
    List<Theme> getAllThemes();
}
