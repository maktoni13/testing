package ua.kpi.training.model.service.impl;

import ua.kpi.training.model.dao.DAOFactory;
import ua.kpi.training.model.dao.ThemeDAO;
import ua.kpi.training.model.entity.Theme;
import ua.kpi.training.model.service.ThemeService;

import java.util.List;

public class ThemeServiceImpl implements ThemeService {
    private DAOFactory daoFactory = DAOFactory.getInstance();

    @Override
    public List<Theme> getAllThemes() {
        try (ThemeDAO themeDAO = daoFactory.createThemeDAO()){
            return themeDAO.findAll();
        }
    }
}
