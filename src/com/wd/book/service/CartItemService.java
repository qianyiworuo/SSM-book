package com.wd.book.service;

import com.wd.book.pojo.Cart;
import com.wd.book.pojo.CartItem;
import com.wd.book.pojo.User;

import java.util.List;

public interface CartItemService {
    boolean addCart(CartItem cartItem);
    boolean updateCart(CartItem cartItem);
    void addOrUpdateCart(CartItem cartItem, Cart cart);
    List<CartItem> getCartItemList(User user);
    Cart getCart(User user);
    int delCartItem(Integer id);
}
