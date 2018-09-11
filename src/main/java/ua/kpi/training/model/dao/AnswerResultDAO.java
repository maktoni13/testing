package ua.kpi.training.model.dao;

import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.entity.Answer;

import java.util.List;

/**
 * Interface Answer Result DAO
 * <p> interface for Answer Result DAO
 *
 * @author Anton Makukhin
 */
public interface AnswerResultDAO extends GenericDAO<Answer> {
    boolean deleteList(List<Answer> answerList) throws DAOException;
    boolean createList(List<Answer> answerList) throws DAOException;
    boolean updateChosenList(List<Answer> answerList) throws DAOException;
    List<Integer> getIncorrectQuestionsIds(int idSummary);
}
