package com.wd.book.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wd.book.pojo.Book;
import com.wd.book.pojo.Cart;
import com.wd.book.pojo.CartItem;
import com.wd.book.pojo.User;
import com.wd.book.service.CartItemService;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;

public class CartItemController {
    private CartItemService cartItemService;
    //加載當前用戶購物車信息
    public String index(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser", user);
        //return "index";
        return "cart/cart";
    }
    public String addCart(Integer bookId, HttpSession session){
        //1.如果當前用戶購物車中已存在所選圖書，就將購物車中的圖書數量+1。
        //2.如果購物車中不存在，則在購物車中新增CartItem，數量為1。
        User user = (User) session.getAttribute("currUser");
        Book book = new Book(bookId);
        CartItem cartItem = new CartItem(book, 1, user);
        cartItemService.addOrUpdateCart(cartItem, user.getCart());

        return "redirect:cart.do";
    }
    public String editCart(Integer cartItemId, Integer orderCount){
        CartItem cartItem = new CartItem(cartItemId);
        cartItem.setBuyCount(orderCount);
        boolean b = cartItemService.updateCart(cartItem);
        if(b){
            return "redirect:cart.do";
        }
        return null;
    }
    //异步请求方法
    public String cartInfo(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        //调用cart实体类中总金额，与商品总数量方法，获得总金额，商品总数量。
        cart.getTotalPrice();
        cart.getTotalCount();
        Gson gson = new GsonBuilder().create();
        String cartStr = gson.toJson(cart);
        return "json:"+cartStr;
    }
    public String editCartInfo(Integer cartItemId, Integer orderCount){
        CartItem cartItem = new CartItem(cartItemId);
        cartItem.setBuyCount(orderCount);
        boolean b = cartItemService.updateCart(cartItem);
        if(b){
            return "json:"+110;
        }
        return null;
    }

}
