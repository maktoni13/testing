package ua.kpi.training.controller.command;

import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.IndexPageService;
import ua.kpi.training.model.service.RegistrationService;

import javax.servlet.http.HttpServletRequest;

public class IndexPageCommand implements Command{
    private IndexPageService indexPageService;

    public IndexPageCommand(IndexPageService indexPageService) {
        this.indexPageService = indexPageService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page = PageContainer.WEB_INF_INDEX_JSP;



        return page;
    }
}
