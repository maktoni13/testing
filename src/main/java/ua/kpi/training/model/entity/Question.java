package ua.kpi.training.model.entity;

import java.util.List;

public class Question {
    private Test test;
    private int id;
    private int idLocal;
    private String description;
    private String descriptionUA;
    private boolean correct;
    private List<Answer> answers;


}
