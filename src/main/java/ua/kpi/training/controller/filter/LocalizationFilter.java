package ua.kpi.training.controller.filter;


import com.sun.deploy.util.StringUtils;
import ua.kpi.training.view.resource.MessageBundle;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebFilter(urlPatterns = {"/*"}, servletNames = {"Servlet"})
public class LocalizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {


//        String localeText = servletRequest.getParameter("language");
//        if (localeText == null) {
//            localeText = "en_EN";
//            servletRequest.setAttribute("language", localeText);
//        }
//        Locale locale = new Locale(localeText);
//        if (!Locale.getDefault().equals(locale)){
//            Locale.setDefault(locale);
//            MessageBundle.updateByDefaultLocale();
//        }
//
//        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
