package ua.kpi.training.controller.filter;

import ua.kpi.training.controller.resource.PageContainer;
import ua.kpi.training.model.entity.enums.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}, servletNames = {"Servlet"})
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest =
                (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse =
                (HttpServletResponse) servletResponse;
        HttpSession httpSession = httpServletRequest.getSession();
        UserType userType = (UserType) httpSession.getAttribute(
                PageContainer.SESSION_AUTHORITY);
        if (userType == null) {
            userType = UserType.GUEST;
            httpSession.setAttribute(PageContainer.SESSION_AUTHORITY,
                    userType);
        }
        String requestURI = httpServletRequest.getRequestURI();
        if (requestURI.contains("admin/")
                && !userType.equals(UserType.ADMIN)) {
            httpServletResponse.sendRedirect(PageContainer.INDEX_PAGE_PATH);
        } else if(requestURI.contains("common/")
                && userType.equals(UserType.GUEST)){
            httpServletResponse.sendRedirect(PageContainer.INDEX_PAGE_PATH);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
