package ua.kpi.training.model.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

public class Summary {
    private int id;
    private boolean informed;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private int questionsQuantity;
    private int correctAnswered;
    private boolean bestResult;
    private Test test;
    private User user;
    private List<Result> results;
    private List<Question> questions;
    private Theme theme;
    private StringBuilder validationResult;

    public Summary() {
        setStartDate(LocalDateTime.now());
        setValidationResult(new StringBuilder());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInformed() {
        return informed;
    }

    public void setInformed(boolean informed) {
        this.informed = informed;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Date getStartSqlDate() {
        return getSqlDateFromLocalDateTime(getStartDate());
    }

    public Date getFinishSqlDate() {
        if (getFinishDate() == null) {
            return getSqlDateFromLocalDateTime(getStartDate());
        }
        return getSqlDateFromLocalDateTime(getFinishDate());
    }

    private Date getSqlDateFromLocalDateTime(LocalDateTime localDateTime) {
        long epochMillis = localDateTime.atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
        return new Date(epochMillis);
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public int getQuestionsQuantity() {
        return questionsQuantity;
    }

    public void setQuestionsQuantity(int questionsQuantity) {
        this.questionsQuantity = questionsQuantity;
    }

    public int getCorrectAnswered() {
        return correctAnswered;
    }

    public void setCorrectAnswered(int correctAnswered) {
        this.correctAnswered = correctAnswered;
    }

    public boolean isBestResult() {
        return bestResult;
    }

    public void setBestResult(boolean bestResult) {
        this.bestResult = bestResult;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        questions.forEach(question -> question.setSummary(this));
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public StringBuilder getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(StringBuilder validationResult) {
        this.validationResult = validationResult;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void appendValidationResult(String validationMessage) {
        validationResult.append(validationMessage);
    }

    public Question getById(int id) {
        return getQuestions()
                .stream()
                .filter(question -> question.getId() == id)
                .findFirst()
                .orElse(null); // TODO: change to Integer / equals
    }

    public Question getByLocalId(int id) {
        return getQuestions()
                .stream()
                .filter(question -> question.getIdLocal() == id)
                .findFirst()
                .orElse(null); // TODO: change to Integer / equals
    }

    public void updateIdLocal() {
        questions.forEach(question -> {
            question.setIdLocal(questions.indexOf(question) + 1);
        });

    }

    public void setStartSqlDate(Date startSqlDate) {
        setStartDate(getLocalDateTimeFromSqlDate(startSqlDate));
    }

    public void setFinishSqlDate(Date finishSqlDate) {
        setFinishDate(getLocalDateTimeFromSqlDate(finishSqlDate));
    }

    private LocalDateTime getLocalDateTimeFromSqlDate(Date sqlDate) {
        return sqlDate.toInstant()
                .atZone(ZoneId.systemDefault()) // Specify the correct timezone
                .toLocalDateTime();
    }

    @Override
    public String toString() {
        return "Summary{" +
                "id=" + id +
                ", informed=" + informed +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", questionsQuantity=" + questionsQuantity +
                ", correctAnswered=" + correctAnswered +
                ", bestResult=" + bestResult +
                ", test=" + test +
                ", user=" + user +
                ", theme=" + theme +
                '}';
    }
}
