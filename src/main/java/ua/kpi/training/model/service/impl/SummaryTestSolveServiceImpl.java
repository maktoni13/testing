package ua.kpi.training.model.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.logger.LoggerMessages;
import ua.kpi.training.model.dao.*;
import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.entity.Answer;
import ua.kpi.training.model.entity.Question;
import ua.kpi.training.model.entity.Summary;
import ua.kpi.training.model.service.SummaryTestSolveService;
import ua.kpi.training.view.resource.MessageBundle;
import ua.kpi.training.view.resource.MessageKey;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SummaryTestSolveServiceImpl implements SummaryTestSolveService {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(TestServiceImpl.class);

    private static final String ANSWERS_CREATION_ERROR = "Answers create error";
    private static final String QUESTIONS_CREATION_ERROR = "Questions create error";

    private DAOFactory daoFactory = DAOFactory.getInstance();

    private Summary getSummaryById(int id, Connection connection) throws DAOException {
        SummaryDAO summaryDAO = daoFactory.createSummaryDAO(connection);
        Summary summary = summaryDAO.findById(id);
        //TestDAO testDAO = daoFactory.createTestDAO(connection);
        //summary.setTest(testDAO.findById(summary.getTest().getId()));
        QuestionResultDAO questionResultDAO = daoFactory.createQuestionResultDAO(connection);
        summary.setQuestions(questionResultDAO.findQuestionsPassingTestId(summary));
        return summary;
    }

    @Override
    public Summary getSummaryEntity(int id) {
        Summary summary = null;
        Connection connection = daoFactory.getConnection();
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            summary = getSummaryById(id, connection);
            connection.commit();
        } catch (Exception e) {
            LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE, e);
            try {
                connection.rollback();
            } catch (SQLException eRollback) {
                LOGGER_SLF4J.error(LoggerMessages.ERROR_SERVICE_SQL_EXCEPTION, eRollback);
                if (summary == null) {
                    summary = new Summary();
                    summary.appendValidationResult(MessageBundle.getMessage(MessageKey.SQL_ERROR));
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
        return summary;
    }

    private List<Answer> getAnswersFromQuestionList(List<Question> questionList){
        List<Answer> answerList = new ArrayList<>();
        if (questionList != null) {
            questionList.forEach(question -> answerList.addAll(question.getAnswers()));
        }
        return answerList;
    }

    @Override
    public boolean updateSummaryEntity(Summary summary) {
        Connection connection = daoFactory.getConnection();
        boolean result;
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            SummaryDAO summaryDAO = daoFactory.createSummaryDAO(connection);
            QuestionResultDAO questionResultDAO = daoFactory.createQuestionResultDAO(connection);
            AnswerResultDAO answerResultDAO = daoFactory.createAnswerResultDAO(connection);
            result = summaryDAO.update(summary);

            List<Question> questionList = summary.getQuestions();
//            if((questionList != null)
//                    && !questionResultDAO.updateChosenList(summary.getQuestions())){
//                summary.appendValidationResult(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE);
//                throw new DAOException(QUESTIONS_CREATION_ERROR);
//            }
            List<Answer> answerList = getAnswersFromQuestionList(questionList);
            if((answerList != null)
                    && !answerResultDAO.updateChosenList(answerList)){
                summary.appendValidationResult(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE);
                throw new DAOException(ANSWERS_CREATION_ERROR);
            }
            connection.commit();
        } catch (Exception e) {
            result = false;
            summary.appendValidationResult(LoggerMessages.ERROR_SERVICE_TRANSACTION_INCOMPLETE);
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
}

