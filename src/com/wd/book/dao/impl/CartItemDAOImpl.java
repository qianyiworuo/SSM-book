package com.wd.book.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.wd.book.dao.CartItemDAO;
import com.wd.book.pojo.CartItem;
import com.wd.book.pojo.User;

import java.util.List;

public class CartItemDAOImpl extends BaseDAO<CartItem> implements CartItemDAO {
    @Override
    public boolean insertCart(CartItem cartItem) {
        String sql = "INSERT INTO t_cart_item (id, book, buyCount, userBean) VALUES (0, ?, ?, ?)";
        int insertCount = executeUpdate(sql, cartItem.getBook(), cartItem.getBuyCount(), cartItem.getUserBean().getId());
        if(insertCount > 0){
            return true;
        }
       return false;
    }


    @Override
    public boolean updateCart(CartItem cartItem) {
        String sql = "UPDATE t_cart_item SET buyCount = ? WHERE id = ?";
        int updateCount = executeUpdate(sql, cartItem.getBuyCount(), cartItem.getId());
        if(updateCount > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<CartItem> selectCart(User user) {
        String sql = "SELECT id, book, buyCount, userBean FROM t_cart_item WHERE userBean = ?";
        List<CartItem> cartItemList = executeQuery(sql, user.getId());
        return cartItemList;
    }
}
