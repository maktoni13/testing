package ua.kpi.training.controller.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface Command
 * <p> Common interface for commands
 *
 * @author Anton Makukhin
 */
public interface Command {
    String execute(HttpServletRequest request);
}
