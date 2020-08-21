package com.zhongwang.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Auther: liulonganys
 * @Date: 2020/8/17 11:35
 * @Description:
 */
@Component
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter实例化成功");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入容器之后，执行servlet初始化之前");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("销毁Filter");
    }
}
