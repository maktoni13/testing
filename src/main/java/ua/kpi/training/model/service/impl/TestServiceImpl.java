package ua.kpi.training.model.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.controller.command.dto.TestsListByThemeDTO;
import ua.kpi.training.logger.LoggerMessages;
import ua.kpi.training.model.dao.*;
import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.entity.*;
import ua.kpi.training.model.service.TestService;
import ua.kpi.training.view.resource.MessageBundle;
import ua.kpi.training.view.resource.MessageKey;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestServiceImpl implements TestService {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(TestServiceImpl.class);

    private static final String USER_NOT_FOUND = "Can't identify user!";
    private static final String ERROR_SUMMARY_CREATION = "Can't create summary!";
    private static final String ANSWERS_CREATION_ERROR = "Answers create error";
    private static final String QUESTIONS_CREATION_ERROR = "Questions create error";
    private static final String PREPARING_TO_SAVE_ERROR = "Preparing to save error";
    private static final String THEME_NOT_EXIST = "Theme not exist";

    private DAOFactory daoFactory = DAOFactory.getInstance();

    @Override
    public TestsListByThemeDTO getTestsListByThemeId(int id) {
        Theme theme;
        ThemeDAO themeDAO = daoFactory.createThemeDAO();
        try {
            theme = themeDAO.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e); // TODO: Right exception
        }

        TestDAO testDAO = daoFactory.createTestDAO();
        try {
            List<Test> testList = testDAO.findAllByThemeId(id);
            testList.forEach(test -> test.setTheme(theme));
            return new TestsListByThemeDTO(theme, testList);
        } catch (Exception e) {
            throw new RuntimeException(e); // TODO: Right exception
        }
    }

    @Override
    public boolean saveTestEntity(Test test) {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            TestDAO testDAO = daoFactory.createTestDAO(connection);
            ThemeDAO themeDAO = daoFactory.createThemeDAO(connection);
            QuestionDAO questionDAO = daoFactory.createQuestionDAO(connection);
            AnswerDAO answerDAO = daoFactory.createAnswerDAO(connection);
            if (themeDAO.findById(test.getTheme().getId()) == null) {
                throw new DAOException(THEME_NOT_EXIST);
            }

            if (test.getId() > 0) {
                List<Question> questionList = questionDAO.findQuestionsPassingTestId(test);
                if (!answerDAO.deleteList(getAnswersFromQuestionList(questionList))) {
                    throw new DAOException(PREPARING_TO_SAVE_ERROR);
                }
                if (!questionDAO.deleteList(questionList)) {
                    throw new DAOException(PREPARING_TO_SAVE_ERROR);
                }
                result = testDAO.update(test);
            } else {
                result = testDAO.create(test);
            }

            List<Question> questionList = test.getQuestions();
            if((questionList != null)
                    && !questionDAO.createList(test.getQuestions())){
                test.appendValidationResult(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE);
                throw new DAOException(QUESTIONS_CREATION_ERROR);
            }
            List<Answer> answerList = getAnswersFromQuestionList(questionList);
            if((answerList != null)
                    && !answerDAO.createList(answerList)){
                test.appendValidationResult(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE);
                throw new DAOException(ANSWERS_CREATION_ERROR);
            }
            connection.commit();
        } catch (Exception e) {
            result = false;
            test.appendValidationResult(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE);
            // TODO: add validation result test and clear hardcode upper
            LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE, e);
            try {
                connection.rollback();
            } catch (SQLException eRollback) {
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, eRollback);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException eClose) {
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, eClose);
            }
        }
        return result;
    }

    private List<Answer> getAnswersFromQuestionList(List<Question> questionList){
        List<Answer> answerList = new ArrayList<>();
        if (questionList != null) {
            questionList.forEach(question -> answerList.addAll(question.getAnswers()));
        }
        return answerList;
    }

    private Test getTestById(int id, Connection connection) throws DAOException{
        TestDAO testDAO = daoFactory.createTestDAO(connection);
        Test test = testDAO.findById(id);
        ThemeDAO themeDAO = daoFactory.createThemeDAO(connection);
        test.setTheme(themeDAO.findById(test.getThemeId()));
        QuestionDAO questionDAO = daoFactory.createQuestionDAO(connection);
        test.setQuestions(questionDAO.findQuestionsPassingTestId(test));
        return test;
    }


    @Override
    public Test getTestEntity(int id) {

        Test test = null;
        Connection connection = daoFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            test = getTestById(id, connection);
            connection.commit();
        } catch (Exception e) {
            LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE, e);
            try {
                connection.rollback();
            } catch (SQLException eRollback) {
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, eRollback);
                if (test == null) {
                    test = new Test();
                    test.appendValidationResult(MessageBundle.getMessage(MessageKey.SQL_ERROR));
                }
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException eClose) {
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, eClose);
            }
        }
        return test;
    }

    @Override
    public int prepareSummaryForTestPassing(int id, String username) {
        int result = 0;
        Connection connection = daoFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            UserDAO userDAO = daoFactory.createUserDAO();
            User user = userDAO.findUserByUsername(username);
            if (user == null){
                throw new DAOException(USER_NOT_FOUND);
            }
            Test test = getTestById(id, connection);
            Summary summary = new Summary();
            summary.setTest(test);
            summary.setQuestions(test.getQuestions());
            summary.setQuestionsQuantity(summary.getQuestions().size());
            summary.setUser(user);
            SummaryDAO summaryDAO = daoFactory.createSummaryDAO(connection);
            if (!summaryDAO.create(summary)){
                throw new DAOException(ERROR_SUMMARY_CREATION);
            }
            QuestionResultDAO questionResultDAO = daoFactory.createQuestionResultDAO(connection);
            List<Question> questionList = summary.getQuestions();
            if((questionList != null)
                    && !questionResultDAO.createList(test.getQuestions())){
                test.appendValidationResult(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE);
                throw new DAOException(QUESTIONS_CREATION_ERROR);
            }
            AnswerResultDAO answerResultDAO = daoFactory.createAnswerResultDAO();
            List<Answer> answerList = getAnswersFromQuestionList(questionList);
            if((answerList != null)
                    && !answerResultDAO.createList(answerList)){
                test.appendValidationResult(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE);
                throw new DAOException(ANSWERS_CREATION_ERROR);
            }
            connection.commit();

            result = summary.getId();
            connection.commit();
        } catch (Exception e) {
            LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE, e);
            try {
                connection.rollback();
            } catch (SQLException eRollback) {
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, eRollback);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException eClose) {
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, eClose);
            }
        }
        return result;
    }
}
