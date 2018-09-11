package ua.kpi.training.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class Question
 * DTO for Question
 *
 * @author Anton Makukhin
 */
public class Question{
    private Test test;
    private Summary summary;
    private int id;
    private int idLocal;
    private String description;
    private String descriptionUA;
    private boolean incorrect;
    private boolean answered;
    private List<Answer> answers;

    public Question() {
        this.answers = new ArrayList<>();
    }

    public Test getTest() {
        return test;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionUA() {
        return descriptionUA;
    }

    public void setDescriptionUA(String descriptionUA) {
        this.descriptionUA = descriptionUA;
    }

    public boolean isIncorrect() {
        return incorrect;
    }

    public void setIncorrect(boolean incorrect) {
        this.incorrect = incorrect;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
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
        return "Question{" +
                "id=" + id +
                ", idLocal=" + idLocal +
                ", description='" + description + '\'' +
                ", descriptionUA='" + descriptionUA + '\'' +
                ", incorrect=" + incorrect +
                ", answered=" + answered +
                '}';
    }
}
