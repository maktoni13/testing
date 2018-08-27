package ua.kpi.training.controller.filter;

import ua.kpi.training.controller.resource.PageContainer;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType(PageContainer.CONTENT_TYPE);
        servletResponse.setCharacterEncoding(PageContainer.CHARACTER_ENCODING);
        servletRequest.setCharacterEncoding(PageContainer.CHARACTER_ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
