package ua.kpi.training.model.dao;

import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.entity.Answer;

import java.util.List;

/**
 * Interface Answer DAO
 * <p> interface for Answer DAO
 *
 * @author Anton Makukhin
 */
public interface AnswerDAO extends GenericDAO<Answer> {
    boolean deleteList(List<Answer> answerList) throws DAOException;
    boolean createList(List<Answer> answerList) throws DAOException;

}
