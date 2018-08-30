package ua.kpi.training.model.service.impl;

import ua.kpi.training.model.dao.DAOFactory;
import ua.kpi.training.model.dao.UserDAO;
import ua.kpi.training.model.entity.User;
import ua.kpi.training.model.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private DAOFactory daoFactory = DAOFactory.getInstance();

    public StudentServiceImpl() {
    }

    @Override
    public List<User> getAllStudents() {
        try (UserDAO userDAO = daoFactory.createUserDAO()){
            return userDAO.findAll();
        }
    }
}
