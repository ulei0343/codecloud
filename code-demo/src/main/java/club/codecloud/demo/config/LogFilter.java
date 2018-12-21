package club.codecloud.demo.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author lei
 */
//@WebFilter(filterName = "logFilter", urlPatterns = "/*")
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LogFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LogFilter:pre");

        String id = request.getParameter("id");
        System.out.println("filter" + id);
        filterChain.doFilter(request, response);
        System.out.println("LogFilter:end");
    }

    @Override
    public void destroy() {
        System.out.println("LogFilter:destroy");
    }
}
