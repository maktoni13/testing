package ua.kpi.training.model.dao.mapper;

import ua.kpi.training.model.entity.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Class Answer mapper
 * <p> Mapper for object Answer from answer database table
 *
 * @author Anton Makukhin
 */
public class AnswerMapper implements ObjectMapper<Answer> {
    private static final String ID_COLUMN = "answer.id";
    private static final String ID_LOCAL_COLUMN = "answer.id_local";
    private static final String DESCRIPTION_COLUMN = "answer.description";
    private static final String DESCRIPTION_UA_COLUMN = "answer.description_ua";
    private static final String CORRECT_FLAG_COLUMN = "answer.correct_flag";

    @Override
    public Answer extractFromResultSet(ResultSet resultSet) throws SQLException {
        Answer answer = new Answer();
        answer.setId(resultSet.getInt(ID_COLUMN));
        answer.setIdLocal(resultSet.getInt(ID_LOCAL_COLUMN));
        answer.setDescription(resultSet.getString(DESCRIPTION_COLUMN));
        answer.setDescriptionUA(resultSet.getString(DESCRIPTION_UA_COLUMN));
        answer.setCorrect(resultSet.getBoolean(CORRECT_FLAG_COLUMN));
        return answer;
    }

    @Override
    public Answer makeUnique(Map<Integer, Answer> map, Answer objectT) {
        map.putIfAbsent(objectT.getId(), objectT);
        return map.get(objectT.getId());
    }
}
