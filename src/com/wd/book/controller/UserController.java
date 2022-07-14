package com.wd.book.controller;

import com.wd.book.pojo.Cart;
import com.wd.book.pojo.User;
import com.wd.book.service.CartItemService;
import com.wd.book.service.UserService;

import javax.servlet.http.HttpSession;

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
}