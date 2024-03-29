package com.zelin.web.filter;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import com.zelin.web.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/student/*")
public class LiginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("登录过滤器开始。。。。。");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user != null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            response.sendRedirect(request.getContextPath()+"/login.jsp");
//            request.getRequestDispatcher(request.getContextPath()+"/login.jsp").forward(request,response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("登录过滤器销毁。。。。。");
    }
}
