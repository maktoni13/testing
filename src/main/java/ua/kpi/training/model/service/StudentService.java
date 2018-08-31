package ua.kpi.training.model.service;

import ua.kpi.training.model.entity.User;

import java.util.List;

public interface StudentService extends CommonService{
    List<User> getAllStudents();
}
