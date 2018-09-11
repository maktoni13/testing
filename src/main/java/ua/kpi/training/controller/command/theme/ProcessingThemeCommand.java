package ua.kpi.training.controller.command.theme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.controller.command.Command;
import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.logger.LoggerMessages;
import ua.kpi.training.model.entity.Theme;
import ua.kpi.training.model.service.ThemeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p> Class provides full cycle of theme entity processing (Add, Edit, Delete)
 *
 * @author Maktoni
 */
public class ProcessingThemeCommand implements Command {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(ProcessingThemeCommand.class);
    private static final String THEME_ID_PARAM = "themeid";
    private static final String REQ_ATTR_THEME = "theme";
    private static final String SESSION_ATTR_THEME = "theme";
    private static final String REQ_PARAM_THEME_NAME = "themeName";
    private static final String REQ_PARAM_THEME_NAME_UA = "themeNameUA";
    private static final String REQ_PARAM_THEME_DESC = "themeDescription";
    private static final String REQ_PARAM_THEME_DESC_UA = "themeDescriptionUA";
    private static final String REQ_ATTR_ERROR = "savingErrorMessage";
    private ThemeService themeService;

    public ProcessingThemeCommand() {
    }

    public ProcessingThemeCommand(ThemeService themeService) {
        this.themeService = themeService;
    }


    private Theme extractThemeFromRequest(HttpServletRequest request){
        Theme theme = (Theme) request.getSession().getAttribute(SESSION_ATTR_THEME);
        if (theme == null){
            theme = new Theme();
        }
        theme.setName((String) request.getParameter(REQ_PARAM_THEME_NAME));
        theme.setNameUA((String) request.getParameter(REQ_PARAM_THEME_NAME_UA));
        theme.setDescription((String) request.getParameter(REQ_PARAM_THEME_DESC));
        theme.setDescriptionUA((String) request.getParameter(REQ_PARAM_THEME_DESC_UA));
        return theme;
    }

    private void saveThemeData(HttpServletRequest request, HttpSession session, Theme theme){
        request.setAttribute(REQ_ATTR_THEME, theme);
        session.setAttribute(SESSION_ATTR_THEME, theme);
        request.setAttribute(REQ_ATTR_ERROR, theme.getValidationResult());
    }

    @Override
    public String execute(HttpServletRequest request) {
        Theme theme;
        HttpSession session = request.getSession();
        String pageThemeProcess = PageContainer.WEB_INF_ADMIN_THEME_JSP;
        String redirectThemesPage = PageContainer.PATH_PREFIX_REDIRECT +
                PageContainer.PATH_COMMAND_THEMES;
        if (PageContainer.HTTP_POST.equals(request.getMethod())) {
            theme = extractThemeFromRequest(request);
            if (!themeService.saveThemeEntity(theme)){
                LOGGER_SLF4J.debug(LoggerMessages.DEBUG_SAVE_THEME_ENTITY_INCOMPLETE);
                LOGGER_SLF4J.debug(theme.toString());
                saveThemeData(request, session, theme);
                return pageThemeProcess;
            };
            request.removeAttribute(REQ_ATTR_THEME);
            return redirectThemesPage;
        }

        int themeId;
        String testIdText = request.getParameter(THEME_ID_PARAM);
        if (testIdText == null) {
            theme = new Theme();
            request.setAttribute(REQ_ATTR_THEME, theme);
            session.setAttribute(SESSION_ATTR_THEME, theme);
            return pageThemeProcess;
        }
        try {
            themeId = (int) Integer.parseInt(testIdText);
        } catch (NumberFormatException e) {
            return redirectThemesPage;
        }
        theme = themeService.getThemeEntity(themeId);
        if (theme == null) {
            return redirectThemesPage;
        }
        request.setAttribute(REQ_ATTR_THEME, theme);
        session.setAttribute(SESSION_ATTR_THEME, theme);
        return pageThemeProcess;
    }


}
