package com.wd.zbookFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//@WebFilter(urlPatterns = {"*.do","*.html"}, initParams = {@WebInitParam(name = "White",value = "/page.do?operate=page&page=user/login,/user.do?null")})
public class SessionFilter implements Filter {
    List<String> whiteList = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String white = filterConfig.getInitParameter("White");
        String[] whiteArr = white.split(",");
        whiteList = Arrays.asList(whiteArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("request.getRequestURL() ="+request.getRequestURL());
        System.out.println("request.getRequestURI() =" +request.getRequestURI());
        System.out.println("request.QueryString() ="+ request.getQueryString());
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        String str = requestURI +"?"+ queryString;
        if(whiteList.contains(str)){
            filterChain.doFilter(request, response);
            return;
        }else {
            HttpSession session = request.getSession();
            Object user = session.getAttribute("currUser");
            if(user == null){
                response.sendRedirect("page.do?operate=page&page=user/login");
            }else {
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
