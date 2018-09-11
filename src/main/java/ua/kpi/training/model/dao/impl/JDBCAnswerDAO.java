package ua.kpi.training.model.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.logger.LoggerMessages;
import ua.kpi.training.model.dao.AnswerDAO;
import ua.kpi.training.model.dao.exception.DAOException;
import ua.kpi.training.model.dao.factory.JDBCDAOFactory;
import ua.kpi.training.model.dao.resource.DAOBundle;
import ua.kpi.training.model.dao.resource.DAOKey;
import ua.kpi.training.model.entity.Answer;
import ua.kpi.training.view.resource.MessageBundle;
import ua.kpi.training.view.resource.MessageKey;

import java.sql.*;
import java.util.List;


/**
 * Class JDBC Answer DAO
 * <p> implementation of DAO for answer database table
 *
 * @author Anton Makukhin
 */
public class JDBCAnswerDAO extends JDBCAbstractDAO<Answer> implements AnswerDAO {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(JDBCAnswerDAO.class);
    private static final String PREFIX_OR = " OR ";
    private static final String APPEND_ID = "id = ";
    protected Connection connection;

    public JDBCAnswerDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Answer findById(int id) {
        return null;
    }

    @Override
    public List<Answer> findAll() {
        return null;
    }

    @Override
    public boolean create(Answer entity) {
        return false;
    }

    @Override
    public boolean update(Answer entity) {
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

    private void appendString(StringBuilder stringBuilder, String appSting, String prefix) {
        if (!"".equals(stringBuilder.toString())) {
            stringBuilder.append(prefix);
        }
        stringBuilder.append(appSting);
    }

    @Override
    public boolean deleteList(List<Answer> answerList) throws DAOException {
        try (PreparedStatement ps = this.connection.
                prepareStatement(
                        DAOBundle.getStatement(
                                DAOKey.DELETE_ANSWER_BY_LIST))) {
            for (Answer answer : answerList) {
                ps.setInt(1, answer.getId());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            LOGGER_SLF4J.error(LoggerMessages.ERROR_DAO_DELETE_QUERY);
            throw new DAOException(
                    MessageBundle.getMessage(MessageKey.SQL_ERROR), e);
        }
        return true;
    }

    @Override
    public boolean createList(List<Answer> answerList) throws DAOException {
        return createEntityList(answerList,
                DAOBundle.getStatement(DAOKey.INSERT_ANSWER),
                connection);
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
        ps.setInt(5, entity.getQuestion().getId());
    }

    @Override
    public void fillUpdatePrepareStatement(PreparedStatement ps, Answer entity) throws SQLException {

    }

    @Override
    public void setKeyToEntity(Answer entity, ResultSet rs) throws SQLException {

    }
}
