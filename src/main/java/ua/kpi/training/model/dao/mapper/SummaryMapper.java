package ua.kpi.training.model.dao.mapper;

import ua.kpi.training.model.entity.Summary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Map;

public class SummaryMapper implements ObjectMapper<Summary> {
    private static final String ID_COLUMN = "summary.id";
    private static final String INFORMED_COLUMN = "summary.informed_flag";
    private static final String START_DATE_COLUMN = "summary.start_date";
    private static final String FINISH_DATE_COLUMN = "summary.finish_date";
    private static final String QUESTION_QUANTITY_COLUMN = "summary.questions_quantity";
    private static final String CORRECT_ANSWERED_COLUMN = "summary.correct_answered";
    private static final String BEST_RESULT_COLUMN = "summary.best_result_flag";

    @Override
    public Summary extractFromResultSet(ResultSet resultSet) throws SQLException {
        Summary summary = new Summary();
        summary.setId(resultSet.getInt(ID_COLUMN));
        summary.setInformed(resultSet.getBoolean(INFORMED_COLUMN));
        //summary.setStartSqlDate(resultSet.getDate(START_DATE_COLUMN));
        //summary.setFinishSqlDate(resultSet.getDate(FINISH_DATE_COLUMN));
        summary.setQuestionsQuantity(resultSet.getInt(QUESTION_QUANTITY_COLUMN));
        summary.setCorrectAnswered(resultSet.getInt(CORRECT_ANSWERED_COLUMN));
        summary.setBestResult(resultSet.getBoolean(BEST_RESULT_COLUMN));
        return summary;
    }

    @Override
    public Summary makeUnique(Map<Integer, Summary> map, Summary objectT) {
        map.putIfAbsent(objectT.getId(), objectT);
        return map.get(objectT.getId());
    }
}


