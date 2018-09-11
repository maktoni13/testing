package ua.kpi.training.model.entity;

import java.util.List;
import java.util.Objects;

/**
 * Class Test
 * DTO for Test
 *
 * @author Anton Makukhin
 */
public class Test {
    private Theme theme;
    private int themeId;
    private int id;
    private int idLocal;
    private String name;
    private String nameUA;
    private String description;
    private String descriptionUA;
    private boolean inactive;
    private List<Question> questions;
    private StringBuilder validationResult;

    public Test() {
        validationResult = new StringBuilder();
    }

    public StringBuilder getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(StringBuilder validationResult) {
        this.validationResult = validationResult;
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

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void appendValidationResult(String validationMessage) {
        validationResult.append(validationMessage);
    }

    public Question getQuestionByLocalId(int id) {
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
        return "Test{" +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", descriptionUA='" + descriptionUA + '\'' +
                ", inactive=" + inactive +
                '}';
    }


}
