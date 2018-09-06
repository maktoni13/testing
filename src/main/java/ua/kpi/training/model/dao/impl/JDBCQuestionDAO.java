package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.QuestionDAO;
import ua.kpi.training.model.dao.mapper.AnswerMapper;
import ua.kpi.training.model.dao.mapper.ObjectMapper;
import ua.kpi.training.model.dao.mapper.QuestionMapper;
import ua.kpi.training.model.dao.mapper.TestMapper;
import ua.kpi.training.model.dao.resource.DAOKeyContainer;
import ua.kpi.training.model.dao.resource.DAOResourceBundle;
import ua.kpi.training.model.entity.Answer;
import ua.kpi.training.model.entity.Question;
import ua.kpi.training.model.entity.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCQuestionDAO implements QuestionDAO {
    private Connection connection;

    public JDBCQuestionDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Question entity) {

    }

    @Override
    public Question findById(int id) {
        return null;
    }

    @Override
    public List<Question> findAll() {
        return null;
    }

    @Override
    public void update(Question entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Question> findQuestionsPassingTestId(int id) {
        Map<Integer, Question> questionMap = new HashMap<>();

        try (PreparedStatement preparedStatement = connection.
                prepareStatement(
                        DAOResourceBundle.getStatement(
                                DAOKeyContainer.SELECT_QUESTIONS_BY_THEME_ID))) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ObjectMapper<Question> questionMapper = new QuestionMapper();
            ObjectMapper<Answer> answerMapper = new AnswerMapper();

            while (resultSet.next()) {
                Question question = questionMapper.extractFromResultSet(resultSet);
                Answer answer = answerMapper.extractFromResultSet(resultSet);
                question = questionMapper.makeUnique(questionMap, question);
                question.getAnswers().add(answer);
            }
            return new ArrayList<>(questionMap.values());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
