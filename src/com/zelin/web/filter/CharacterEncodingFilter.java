package com.zelin.web.filter;

import org.omg.IOP.Encoding;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "/*",initParams = {@WebInitParam(name="Encoding",value ="UTF-8")})
public class CharacterEncodingFilter implements Filter {
    String encoding;
    public void destroy() {
        System.out.println("销毁编码格式 = "+encoding);
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("设置编码格式 = ");
        req.setCharacterEncoding("UTF-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("Encoding");
        System.out.println("初始化编码格式 = "+encoding);
    }
}
