package ua.kpi.training.controller.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.IndexPageService;

import javax.servlet.http.HttpServletRequest;

/**
 * Class Index page command
 * <p> Implementation of index page command
 * Should include in the future resolving of common
 * application statistic from DB
 *
 * @author Anton Makukhin
 */
public class IndexPageCommand implements Command {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(IndexPageCommand.class);

    public IndexPageCommand() {
    }

    @Override
    public String execute(HttpServletRequest request) {
        return PageContainer.WEB_INF_INDEX_JSP;
    }
}
