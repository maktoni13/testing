package ua.kpi.training.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Class Summary
 * DTO for Summary
 *
 * @author Anton Makukhin
 */
public class Summary{
    private int id;
    private boolean informed;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private int questionsQuantity;
    private int correctAnswered;
    private boolean bestResult;
    private Test test;
    private User user;
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

    public void appendValidationResult(String validationMessage) {
        validationResult.append(validationMessage);
    }

    public Question getByLocalId(int id) {
        return getQuestions()
                .stream()
                .filter(question -> question.getIdLocal() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 37 * result + Objects.hash(getId());
        return result;
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
