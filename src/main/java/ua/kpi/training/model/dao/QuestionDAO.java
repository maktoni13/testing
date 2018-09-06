package ua.kpi.training.model.dao;

import ua.kpi.training.model.entity.Question;

import java.util.List;

public interface QuestionDAO extends GenericDAO<Question> {
    List<Question> findQuestionsPassingTestId(int id);
}
