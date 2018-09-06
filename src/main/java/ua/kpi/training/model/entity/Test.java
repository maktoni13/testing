package ua.kpi.training.model.entity;

import java.util.List;

public class Test {
    private Theme theme;
    private int themeId;
    private int id;
    private int idLocal;
    private String name;
    private String nameUA;
    private String description;
    private String descriptionUA;
    private boolean correct;
    private boolean inactive;
    private boolean chosen;
    private List<Question> questions;

    public Test() {
    }

    public Theme getTheme() {
        return theme;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUA() {
        return nameUA;
    }

    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
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

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setLocalQuestionsId() {
        questions.forEach(question -> question.setIdLocal(questions.indexOf(question) + 1));
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

    @Override
    public String toString() {
        return "Test{" +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", descriptionUA='" + descriptionUA + '\'' +
                ", correct=" + correct +
                ", inactive=" + inactive +
                '}';
    }
}
