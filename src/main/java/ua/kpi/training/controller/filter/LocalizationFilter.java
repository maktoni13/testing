package ua.kpi.training.controller.filter;


import com.sun.deploy.util.StringUtils;
import ua.kpi.training.controller.command.utility.ConfigurationContainer;
import ua.kpi.training.view.resource.MessageBundle;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebFilter(urlPatterns = {"/*"}, servletNames = {"Servlet"})
public class LocalizationFilter implements Filter {
    private final static String SESSION_USER_LOCALE_PARAM = "lang";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpServletRequest.getSession();

        Locale currentLocale;

        String currentLocaleText = (String) httpSession.
                getAttribute(SESSION_USER_LOCALE_PARAM);

        currentLocale = (currentLocaleText == null) ?
                ConfigurationContainer.DEFAULT_LOCALE :
                Locale.forLanguageTag(currentLocaleText);

        if (!ConfigurationContainer.SUPPORTED_LOCALE_LIST.contains(currentLocale)) {
            currentLocale = ConfigurationContainer.DEFAULT_LOCALE;
            httpSession.setAttribute(SESSION_USER_LOCALE_PARAM,
                    currentLocale.toString());
        }

        if (!Locale.getDefault().equals(currentLocale)) {
            Locale.setDefault(currentLocale);
            MessageBundle.updateByDefaultLocale();
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
