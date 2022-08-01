package com.wd.book.controller;

import com.wd.book.pojo.Cart;
import com.wd.book.pojo.User;
import com.wd.book.service.CartItemService;
import com.wd.book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController {
    private UserService userService;
    private CartItemService cartItemService;
    public String login(String uname, String pwd, HttpSession session){
        User user = userService.login(uname, pwd);
        if(user != null){
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            session.setAttribute("currUser", user);
            return "redirect:book.do";
        }
        return "user/login";
    }
    public String regist(String uname, String pwd, String email, String verifyCode, HttpSession session, HttpServletResponse response) throws IOException {
        String sessionKey = (String)session.getAttribute("KAPTCHA_SESSION_KEY");
        if(sessionKey == null || !verifyCode.equals(sessionKey)){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script language='javascript'>alert('验证码不正确，请重新输入！')</script>");
            return "user/regist";
        }
        else {
            if(sessionKey.equals(verifyCode)){
                User user = new User();
                user.setUname(uname);
                user.setPwd(pwd);
                user.setEmail(email);
                user.setRole(0);
                int iCount = userService.regist(user);
                if(iCount > 0){
                    return "user/login";
                }
            }
        }
        return "user/login";
    }
    public String chkUname(String uname){
        User user = userService.chkUname(uname);
        if (user != null){
            //用户名已存在，不能注册
            return "json:{'uname' : '1'}";
        }else {
            //可以注册
            return "json:{'uname' : '0'}";
        }
    }
}