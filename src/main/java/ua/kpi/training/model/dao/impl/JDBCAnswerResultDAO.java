package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.AnswerResultDAO;
import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.dao.resource.DAOBundle;
import ua.kpi.training.model.dao.resource.DAOKey;
import ua.kpi.training.model.entity.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCAnswerResultDAO extends JDBCAbstractDAO<Answer> implements AnswerResultDAO {
    protected Connection connection;

    public JDBCAnswerResultDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean deleteList(List<Answer> answerList) throws DAOException {
        return false;
    }

    @Override
    public boolean createList(List<Answer> answerList) throws DAOException {
        return createEntityList(answerList,
                DAOBundle.getStatement(DAOKey.INSERT_ANSWER_RESULT),
                connection);
    }

    @Override
    public boolean create(Answer entity) throws DAOException {
        return false;
    }

    @Override
    public Answer findById(int id) throws DAOException {
        return null;
    }

    @Override
    public List<Answer> findAll() throws DAOException {
        return null;
    }

    @Override
    public boolean update(Answer entity) throws DAOException {
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
    public void fillDeletePrepareStatement(PreparedStatement ps, Answer entity) throws SQLException {

    }

    @Override
    public void fillInsertPrepareStatement(PreparedStatement ps, Answer entity) throws SQLException {
        ps.setInt(1, entity.getIdLocal());
        ps.setString(2, entity.getDescription());
        ps.setString(3, entity.getDescriptionUA());
        ps.setBoolean(4, entity.isCorrect());
        ps.setBoolean(5, entity.isChosen());
        ps.setInt(6, entity.getQuestion().getId());
    }


    @Override
    public void setKeyToEntity(Answer entity, ResultSet rs) throws SQLException {

    }

    @Override
    public boolean updateChosenList(List<Answer> answerList) throws DAOException {
        return updateEntityList(answerList,
                DAOBundle.getStatement(DAOKey.UPDATE_ANSWER_RESULT_BY_LIST),
                connection);
    }

    @Override
    public void fillUpdatePrepareStatement(PreparedStatement ps, Answer entity) throws SQLException {
        ps.setBoolean(1, entity.isChosen());
        ps.setInt(2, entity.getIdLocal());
    }
}