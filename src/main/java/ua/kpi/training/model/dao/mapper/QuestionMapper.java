package ua.kpi.training.model.dao.mapper;

import ua.kpi.training.model.entity.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class QuestionMapper implements ObjectMapper<Question> {
    private static final String ID_COLUMN = "id";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String DESCRIPTION_UA_COLUMN = "description_ua";

    @Override
    public Question extractFromResultSet(ResultSet resultSet) throws SQLException {
        Question question = new Question();
        question.setId(resultSet.getInt(ID_COLUMN));
        question.setDescription(resultSet.getString(DESCRIPTION_COLUMN));
        question.setDescriptionUA(resultSet.getString(DESCRIPTION_UA_COLUMN));
        return question;
    }

    @Override
    public Question makeUnique(Map<Integer, Question> map, Question objectT) {
        map.putIfAbsent(objectT.getId(), objectT);
        return map.get(objectT.getId());
    }
}
