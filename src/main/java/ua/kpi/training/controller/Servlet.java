package ua.kpi.training.controller;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.command.auth.ExceptionCommand;
import ua.kpi.training.controller.command.auth.LoginCommand;
import ua.kpi.training.controller.command.auth.LogoutCommand;
import ua.kpi.training.controller.command.auth.RegistrationCommand;
import ua.kpi.training.controller.command.students.StudentListCommand;
import ua.kpi.training.controller.command.students.ThemeListCommand;
import ua.kpi.training.controller.resource.PageContainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Class Servlet. Controller class for processing http requests
 *
 * @version 1.0 2018-08-18
 * @author Anton Makukhin
 */
public class Servlet extends HttpServlet{

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig){
        servletConfig.getServletContext().setAttribute(PageContainer.CONTEXT_LOGGED_USERS, new HashSet<String>());
        commands.put(PageContainer.COMMAND_EXCEPTION, new ExceptionCommand());
        commands.put(PageContainer.COMMAND_LOGIN, new LoginCommand());
        commands.put(PageContainer.COMMAND_LOGOUT, new LogoutCommand());
        commands.put(PageContainer.COMMAND_REGISTRATION, new RegistrationCommand());
        commands.put(PageContainer.COMMAND_STUDENT_LIST, new StudentListCommand());
        commands.put(PageContainer.COMMAND_THEME_LIST, new ThemeListCommand());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        path = path.replaceAll(PageContainer.PATH_REPLACE_REGEX,
                PageContainer.PATH_REPLACE_REPLACEMENT);
        // Command command = commands.getOrDefault(path,
        //        (r) -> PageContainer.INDEX_PAGE_PATH);
        Command command = commands.getOrDefault(path,
                new LoginCommand());
        String page = command.execute(request);
        RequestDispatcher requestDispatcher = request.getSession()
                .getServletContext()
                .getRequestDispatcher(page);

        requestDispatcher.forward(request, response);
    }


}
