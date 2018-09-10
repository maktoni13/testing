package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.QuestionDAO;
import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.dao.factory.JDBCDAOFactory;
import ua.kpi.training.model.dao.mapper.AnswerMapper;
import ua.kpi.training.model.dao.mapper.ObjectMapper;
import ua.kpi.training.model.dao.mapper.QuestionMapper;
import ua.kpi.training.model.dao.resource.DAOKey;
import ua.kpi.training.model.dao.resource.DAOBundle;
import ua.kpi.training.model.entity.Answer;
import ua.kpi.training.model.entity.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCQuestionDAO extends JDBCAbstractDAO<Question> implements QuestionDAO {
    protected Connection connection;

    public JDBCQuestionDAO(Connection connection) {
        this.connection = connection;
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
    public List<Question> findQuestionsPassingTestId(int id) {
        Map<Integer, Question> questionMap = new HashMap<>();
        try (PreparedStatement ps = connection.
                prepareStatement(
                        DAOBundle.getStatement(
                                DAOKey.SELECT_QUESTIONS_BY_THEME_ID))) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            ObjectMapper<Question> questionMapper = new QuestionMapper();
            ObjectMapper<Answer> answerMapper = new AnswerMapper();

            while (resultSet.next()) {
                Question question = questionMapper.extractFromResultSet(resultSet);
                Answer answer = answerMapper.extractFromResultSet(resultSet);
                question = questionMapper.makeUnique(questionMap, question);
                question.getAnswers().add(answer);
            }
            List<Question> questionList = new ArrayList<>(questionMap.values());
            return questionList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean create(Question entity) {
        return false;
    }

    @Override
    public boolean update(Question entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void close() {
        JDBCDAOFactory.connectionClose(connection);
    }

    @Override
    public boolean deleteList(List<Question> questionList) throws DAOException {
        return deleteEntityList(questionList,
                DAOBundle.getStatement(DAOKey.DELETE_QUESTION_BY_LIST),
                connection);
    }

    @Override
    public void fillDeletePrepareStatement(PreparedStatement ps, Question question) throws SQLException {
        ps.setInt(1, question.getId());
    }

    @Override
    public boolean createList(List<Question> questionList) throws DAOException{
        return createEntityList(questionList,
                DAOBundle.getStatement(DAOKey.INSERT_QUESTION_BY_LIST),
                connection);
    }

    @Override
    public void fillInsertPrepareStatement(PreparedStatement ps, Question entity) throws SQLException {
        ps.setInt(1, entity.getIdLocal());
        ps.setString(2, entity.getDescription());
        ps.setString(3, entity.getDescriptionUA());
        ps.setInt(4, entity.getTest().getId());
    }

    @Override
    public void setKeyToEntity(Question entity, ResultSet rs) throws SQLException{
        entity.setId(rs.getInt(1));
    }

    @Override
    public void fillUpdatePrepareStatement(PreparedStatement ps, Question entity) throws SQLException {
    }
}
