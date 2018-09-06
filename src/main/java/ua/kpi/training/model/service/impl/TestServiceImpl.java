package ua.kpi.training.model.service.impl;

import ua.kpi.training.model.dao.DAOFactory;
import ua.kpi.training.model.dao.TestDAO;
import ua.kpi.training.model.dao.ThemeDAO;
import ua.kpi.training.model.entity.Test;
import ua.kpi.training.model.entity.Theme;
import ua.kpi.training.model.service.TestService;

import java.util.List;

public class TestServiceImpl implements TestService {
    private DAOFactory daoFactory = DAOFactory.getInstance();

    @Override
    public List<Test> getTestsListByThemeId(int id) {
        Theme theme;
        try (ThemeDAO themeDAO = daoFactory.createThemeDAO()) {
            theme = themeDAO.findById(id);
        } catch (Exception e){
            throw new RuntimeException(e); // TODO: Right exception
        }

        try (TestDAO testDAO = daoFactory.createTestDAO()){
            List<Test> testList = testDAO.findAllByThemeId(id);
            testList.forEach(test -> test.setTheme(theme));
            return testList;
        } catch (Exception e){
            throw new RuntimeException(e); // TODO: Right exception
        }
    }
}
