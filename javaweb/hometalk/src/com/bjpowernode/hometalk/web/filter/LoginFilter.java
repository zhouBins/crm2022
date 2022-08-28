package com.bjpowernode.hometalk.web.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
       //类型强制转化 其子类才有getsessi方法
        HttpServletRequest request =(HttpServletRequest)req;

        HttpServletResponse response =(HttpServletResponse)res;


        HttpSession session = request.getSession(false);

        String servletPath = request.getServletPath();


        /**
         * 有些页面是不能过滤的
         * 注册
         * 登录
         * 判断用户是否10天免登录的welcome
         * 主页
         */

        if ("/index.jsp".equals(servletPath)||"/user/login".equals(servletPath)||
                "/reg.jsp".equals(servletPath)||"/welcome".equals(servletPath)||
                (session !=null && session.getAttribute("userid")!= null)){
//1'首先要获得浏览器请求的资源路径
            //继续往下走
            filterChain.doFilter(request,response);

        }else {
            //没有登录就跳到登录页面
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}