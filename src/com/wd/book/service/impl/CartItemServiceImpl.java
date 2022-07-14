package com.wd.book.service.impl;

import com.wd.book.dao.CartItemDAO;
import com.wd.book.pojo.Cart;
import com.wd.book.pojo.CartItem;
import com.wd.book.pojo.User;
import com.wd.book.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl implements CartItemService {
    private CartItemDAO cartItemDAO;
    @Override
    public boolean addCart(CartItem cartItem) {
        boolean bole = cartItemDAO.insertCart(cartItem);
        if(bole){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCart(CartItem cartItem) {
        boolean bole = cartItemDAO.updateCart(cartItem);
        if(bole){
            return true;
        }
        return false;
    }

    @Override
    public void addOrUpdateCart(CartItem cartItem, Cart cart) {
        //判斷當前用戶購物車中是否有這本書的cartItem,有update，無add。
        //1.首先判斷用戶是否有購物車
        if (cart != null){
            //2.得到當前購物車中書的集合
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            //3.判斷購物車中書的集合是否為空
            if (cartItemMap == null){
                cartItemMap = new HashMap<>();
            }else {
                //4.如果不為空，判斷傳過來的cartItem，是否已經存在集合中
                if(cartItemMap.containsKey(cartItem.getBook().getId())){
                    CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                    int buyCount = cartItemTemp.getBuyCount() + 1;
                    cartItemTemp.setBuyCount(buyCount);
                    updateCart(cartItemTemp);
                }else {
                    addCart(cartItem);
                }
            }
            //購物車為空
        }else {
            addCart(cartItem);
        }
    }

    //獲取用戶的購物車信息
    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemList = cartItemDAO.selectCart(user);
        if (cartItemList != null){
            Map<Integer, CartItem> cartItemMap = new HashMap<>();
            for (CartItem cartItem: cartItemList) {
                cartItemMap.put(cartItem.getBook().getId(),cartItem);
            }
            Cart cart = new Cart();
            cart.setCartItemMap(cartItemMap);
            return cart;
        }
        return null;
    }
}
