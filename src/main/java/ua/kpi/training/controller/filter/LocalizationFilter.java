package ua.kpi.training.controller.filter;


import com.sun.deploy.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class LocalizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        Locale locale1 = (Locale) request.getSession().getAttribute("language");

        //res.setLocale(locale);
        //chain.doFilter(req, res);

        //String localeText = servletRequest.getParameter("language");
        //if (localeText == null) {
//            localeText = "en_EN";
//        }
//        Locale locale = new Locale(localeText);
//        Locale.setDefault(locale);
//       servletResponse.setLocale(locale);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
