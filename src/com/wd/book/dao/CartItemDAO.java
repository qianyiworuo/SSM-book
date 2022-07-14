package com.wd.book.dao;

import com.wd.book.pojo.CartItem;
import com.wd.book.pojo.User;

import java.util.List;

public interface CartItemDAO {
    boolean insertCart(CartItem cartItem);
    boolean updateCart(CartItem cartItem);
    List<CartItem> selectCart(User user);
}
