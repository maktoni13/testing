package ua.kpi.training.model.service.impl;

import ua.kpi.training.model.dao.DAOFactory;
import ua.kpi.training.model.dao.QuestionDAO;
import ua.kpi.training.model.dao.TestDAO;
import ua.kpi.training.model.dao.ThemeDAO;
import ua.kpi.training.model.entity.Question;
import ua.kpi.training.model.entity.Test;
import ua.kpi.training.model.entity.Theme;
import ua.kpi.training.model.service.PassingTestService;

public class PassingTestServiceImpl implements PassingTestService {
    private DAOFactory daoFactory = DAOFactory.getInstance();

    public PassingTestServiceImpl() {
    }

    @Override
    public Test getTestById(int id) {

        Test test;
        try {
            TestDAO testDAO = daoFactory.createTestDAO();
            test = testDAO.findById(id);
        } catch (Exception e){
            throw new RuntimeException(e); // TODO: Right exception
        }
        Theme theme;
        try {
            ThemeDAO themeDAO = daoFactory.createThemeDAO();
            theme = themeDAO.findById(test.getThemeId());
        } catch (Exception e){
            throw new RuntimeException(e); // TODO: Right exception
        }

        test.setTheme(theme);

        try {
            QuestionDAO questionDAO = daoFactory.createQuestionDAO();
            test.setQuestions(questionDAO.findQuestionsPassingTestId(test));
        } catch (Exception e){
            throw new RuntimeException(e); // TODO: Right exception
        }
        test.getQuestions().forEach(question -> question.setTest(test));

        return test;
    }
}
