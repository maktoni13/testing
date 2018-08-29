package ua.kpi.training.controller.command;

import ua.kpi.training.model.service.CommonService;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    CommonService getService();
    String execute(HttpServletRequest request);
}
