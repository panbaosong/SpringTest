package com.zhongwang.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: liulonganys
 * @Date: 2020/8/17 09:22
 * @Description:
 * 1.过滤器是servelt规范中的一部分，任何java web工程都可以使用
 * 2.拦截器是SpringMVC框架自己的，只有使用了SpringMVC框架的工程才能使用
 * 3.过滤器是url-pattern中配置了/*之后，可以对所有要访问的资源拦截
 * 4.拦截器它是只会拦截器访问的控制器方法，如果访问的是jsp、html、css、image或者js是不会进行拦截的
 * 5.拦截器可以获取IOC容器中的各个bean，而过滤器不行，在拦截器中注入一个service，可以调用业务逻辑
 *
 * 触发时机
 * 过滤器是在请求进入容器之后，但请求进入servlet之前进行预处理的，请求返回也是，是在servlet处理完后，返回前端之前
 * 拦截器是在controller方法执行之前，controller方法执行之后，DispatcherServlet渲染视图之前，渲染视图之后返回页面之前
 *
 * Filter-servlet-interceptor-controller-interceptor-servlet-Filter  //执行过程
 *
 * 它是AOP思想的具体应用
 * 如果需要自定义拦截器，要求必须实现：HandlerInterceptor
 */
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在拦截点（Controller方法处理之前）执行拦截。若返回false则中断执行
        System.out.println("controller方法执行前");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //在处理过程中（Controller方法处理完之后，DispatcherServlet进行视图的渲染之前）执行拦截
        System.out.println("controller方法执行后,DispatcherServlet进行视图的渲染之前");

    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //在DispatcherServlet进行视图的渲染后 返回前执行拦截
        System.out.println("DispatcherServlet进行视图的渲染后 返回前");
    }

}
