package ua.kpi.training.model.dao;

import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.entity.Question;
import ua.kpi.training.model.entity.Summary;
import ua.kpi.training.model.entity.Test;

import java.util.List;

/**
 * Interface Question Result DAO
 * <p> interface for Question Result DAO
 *
 * @author Anton Makukhin
 */
public interface QuestionResultDAO extends GenericDAO<Question>{
    List<Question> findQuestionsPassingTestId(Summary summary);
    boolean deleteList(List<Question> questionList) throws DAOException;
    boolean createList(List<Question> questionList) throws DAOException;
    boolean updateChosenList(List<Question> questionList) throws DAOException;
}
