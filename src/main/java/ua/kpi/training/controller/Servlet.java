package ua.kpi.training.controller;

import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.command.IndexPageCommand;
import ua.kpi.training.controller.command.auth.*;
import ua.kpi.training.controller.command.students.StudentListCommand;
import ua.kpi.training.controller.command.students.ThemeListCommand;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.service.impl.*;

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
//        Locale.;
        servletConfig.getServletContext().setAttribute(PageContainer.CONTEXT_LOGGED_USERS, new HashSet<String>());
        commands.put(PageContainer.COMMAND_EXCEPTION, new ExceptionCommand());
        commands.put(PageContainer.COMMAND_LOGIN, new LoginCommand(new LoginServiceImpl()));
        commands.put(PageContainer.COMMAND_LOGOUT, new LogoutCommand());
        commands.put(PageContainer.COMMAND_REGISTRATION, new RegistrationCommand(new RegistrationServiceImpl()));
        commands.put(PageContainer.COMMAND_STUDENT_LIST, new StudentListCommand(new StudentServiceImpl()));
        commands.put(PageContainer.COMMAND_THEME_LIST, new ThemeListCommand(new ThemeServiceImpl()));
        commands.put(PageContainer.COMMAND_VIEW_USER_PROFILE, new ViewUserProfileCommand(new ViewUserProfileServiceImpl()));
        commands.put(PageContainer.COMMAND_VIEW_ADMIN_PROFILE, new ViewAdminProfileCommand(new ViewAdminProfileServiceImpl()));
        commands.put(PageContainer.COMMAND_INDEX_PAGE, new IndexPageCommand(new IndexPageServiceImpl()));
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
        Command command = commands.getOrDefault(path,
                (r) -> PageContainer.PATH_COMMAND_INDEX);
        String page = command.execute(request);
        if(page.contains(PageContainer.PATH_PREFIX_REDIRECT)){
            response.sendRedirect(page.replace(PageContainer.PATH_PREFIX_REDIRECT,
                    PageContainer.PATH_REPLACE_REPLACEMENT));
        }else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }


}
