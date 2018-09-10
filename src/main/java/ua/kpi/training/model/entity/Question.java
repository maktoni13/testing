package ua.kpi.training.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private Test test;
    private int id;
    private int idLocal;
    private String description;
    private String descriptionUA;
    private boolean correct;
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

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void setLocalAnswersId() {
        answers.forEach(answer -> {
            answer.setIdLocal(answers.indexOf(answer) + 1);
            answer.setQuestion(this);
        });
    }
}
