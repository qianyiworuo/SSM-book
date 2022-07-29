package com.wd.book.dao;

import com.wd.book.pojo.CartItem;
import com.wd.book.pojo.User;

import java.util.List;

public interface CartItemDAO {
    boolean insertCart(CartItem cartItem);
    boolean updateCart(CartItem cartItem);
    List<CartItem> selectCart(User user);
    //刪除指定的購物車列表項
    int delCartItem(Integer id);
    //更改購物車中訂單商品的數量
}
