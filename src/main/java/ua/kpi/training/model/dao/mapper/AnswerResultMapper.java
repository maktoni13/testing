package ua.kpi.training.model.dao.mapper;

import ua.kpi.training.model.entity.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AnswerResultMapper implements ObjectMapper<Answer> {
    private static final String ID_COLUMN = "answer_result.id";
    private static final String ID_LOCAL_COLUMN = "answer_result.id_local";
    private static final String DESCRIPTION_COLUMN = "answer_result.description";
    private static final String DESCRIPTION_UA_COLUMN = "answer_result.description_ua";

    @Override
    public Answer extractFromResultSet(ResultSet resultSet) throws SQLException {
        Answer answer = new Answer();
        answer.setId(resultSet.getInt(ID_COLUMN));
        answer.setIdLocal(resultSet.getInt(ID_LOCAL_COLUMN));
        answer.setDescription(resultSet.getString(DESCRIPTION_COLUMN));
        answer.setDescriptionUA(resultSet.getString(DESCRIPTION_UA_COLUMN));
        return answer;
    }

    @Override
    public Answer makeUnique(Map<Integer, Answer> map, Answer objectT) {
        map.putIfAbsent(objectT.getId(), objectT);
        return map.get(objectT.getId());
    }
}
