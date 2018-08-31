package ua.kpi.training.model.dao.impl;

import ua.kpi.training.model.dao.AnswerDAO;
import ua.kpi.training.model.dao.PassedTestDAO;
import ua.kpi.training.model.entity.Answer;
import ua.kpi.training.model.entity.PassedTest;

import java.util.List;

public class JDBCPassedTestDAO implements PassedTestDAO {
    @Override
    public void create(PassedTest entity) {

    }

    @Override
    public PassedTest findById(int id) {
        return null;
    }

    @Override
    public List<PassedTest> findAll() {
        return null;
    }

    @Override
    public void update(PassedTest entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}

