package ua.kpi.training.model.dao;

import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.entity.Answer;

import java.util.List;

public interface AnswerResultDAO extends GenericDAO<Answer> {
    boolean deleteList(List<Answer> answerList) throws DAOException;
    boolean createList(List<Answer> answerList) throws DAOException;
    boolean updateChosenList(List<Answer> answerList) throws DAOException;
    List<Integer> getIdsIncorrectAnsweredQuestions(int idSummary);
}
