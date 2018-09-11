package ua.kpi.training.model.service;

import ua.kpi.training.model.entity.Summary;

public interface SummaryTestSolveService {
    Summary getSummaryEntity(int id);
    boolean updateSummaryEntity(Summary summary);

}
