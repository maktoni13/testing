package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.QuestionResultDAO;
import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.dao.mapper.*;
import ua.kpi.training.model.dao.resource.DAOBundle;
import ua.kpi.training.model.dao.resource.DAOKey;
import ua.kpi.training.model.entity.Answer;
import ua.kpi.training.model.entity.Question;
import ua.kpi.training.model.entity.Summary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class JDBC Question Result DAO
 * <p> implementation of DAO for question result database table
 *
 * @author Anton Makukhin
 */
public class JDBCQuestionResultDAO extends JDBCAbstractDAO<Question> implements QuestionResultDAO {
    protected Connection connection;

    public JDBCQuestionResultDAO() {
    }

    public JDBCQuestionResultDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Question entity) throws DAOException {
        return false;
    }

    @Override
    public Question findById(int id) throws DAOException {
        return null;
    }

    @Override
    public List<Question> findAll() throws DAOException {
        return null;
    }

    @Override
    public boolean update(Question entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(int id) throws DAOException {
        return false;
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void fillDeletePrepareStatement(PreparedStatement ps, Question entity) throws SQLException {
        ps.setBoolean(1, entity.isIncorrect());
        ps.setInt(2, entity.getId());
        ps.setInt(1, entity.getIdLocal());
        ps.setString(2, entity.getDescription());
        ps.setString(3, entity.getDescriptionUA());
        ps.setInt(4, entity.getSummary().getId());

    }

    @Override
    public void fillInsertPrepareStatement(PreparedStatement ps, Question entity) throws SQLException {
        ps.setInt(1, entity.getIdLocal());
        ps.setString(2, entity.getDescription());
        ps.setString(3, entity.getDescriptionUA());
        ps.setInt(4, entity.getSummary().getId());
        ps.setBoolean(5, entity.isIncorrect());

    }

    @Override
    public void fillUpdatePrepareStatement(PreparedStatement ps, Question entity) throws SQLException {
        ps.setBoolean(1, entity.isIncorrect());
        ps.setInt(2, entity.getId());


//        UPDATE question_result SET \
//        incorrect_flag = ? \
//        WHERE ( id = ? )
    }

    @Override
    public void setKeyToEntity(Question entity, ResultSet rs) throws SQLException {
        entity.setId(rs.getInt(1));
    }

    @Override
    public boolean deleteList(List<Question> questionList) throws DAOException {
        return false;
    }

    @Override
    public boolean createList(List<Question> questionList) throws DAOException {
        return createEntityList(questionList,
                DAOBundle.getStatement(DAOKey.INSERT_QUESTION_RESULT),
                connection);
    }

    @Override
    public List<Question> findQuestionsPassingTestId(Summary summary) {
        Map<Integer, Question> questionMap = new HashMap<>();
        try (PreparedStatement ps = connection.
                prepareStatement(
                        DAOBundle.getStatement(
                                DAOKey.SELECT_QUESTIONS_RESULT_BY_SUMMARY_ID))) {
            ps.setInt(1, summary.getId());
            ResultSet resultSet = ps.executeQuery();
            ObjectMapper<Question> questionMapper = new QuestionResultMapper();
            ObjectMapper<Answer> answerMapper = new AnswerResultMapper();

            while (resultSet.next()) {
                Question question = questionMapper.extractFromResultSet(resultSet);
                Answer answer = answerMapper.extractFromResultSet(resultSet);
                question = questionMapper.makeUnique(questionMap, question);
                question.getAnswers().add(answer);
                answer.setQuestion(question);
                question.setSummary(summary);
            }
            return new ArrayList<>(questionMap.values());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateChosenList(List<Question> questionList) throws DAOException {
        return updateEntityList(questionList,
                DAOBundle.getStatement(DAOKey.UPDATE_QUESTION_RESULT_BY_LIST),
                connection);
    }
}
