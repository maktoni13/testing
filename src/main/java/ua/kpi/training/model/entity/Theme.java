package ua.kpi.training.model.entity;

import java.util.List;

public class Theme {
    private int id;
    private String name;
    private String nameUA;
    private String description;
    private String descriptionUA;
    private List<Test> tests;

    public Theme() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", descriptionUA='" + descriptionUA + '\'' +
                '}';
    }
}
