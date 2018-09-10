package ua.kpi.training.controller.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.IndexPageService;
import ua.kpi.training.model.service.RegistrationService;

import javax.servlet.http.HttpServletRequest;

public class IndexPageCommand implements Command{
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(IndexPageCommand.class);
    private IndexPageService indexPageService;

    public IndexPageCommand(IndexPageService indexPageService) {
        this.indexPageService = indexPageService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        LOGGER_SLF4J.info("Index page command");
        String page = PageContainer.WEB_INF_INDEX_JSP;



        return page;
    }
}
