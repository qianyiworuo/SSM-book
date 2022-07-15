package com.wd.book.pojo;

import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Integer, CartItem> cartItemMap;//購物車中購物項的集合，key為bookId
    private Double totalPrice;//總金額
    private Integer totalCount;//購物車中購物項數量
    private Integer totalGoods;//購物車中商品的總數量

    public Cart() {
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public double getTotalPrice() {
        //for循環獲得購物車書的總金額
        totalPrice = 0.0;
        if(cartItemMap != null && cartItemMap.size() > 0){
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer, CartItem> cartItemEntry : entries ) {
                CartItem cartItem = cartItemEntry.getValue();
                totalPrice += cartItem.getBook().getPrice() * cartItem.getBuyCount();
            }
        }
        return totalPrice;
    }

    public Integer getTotalCount() {
        totalCount = 0;
        if(cartItemMap != null && cartItemMap.size() > 0){
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }

    public Integer getTotalGoods() {
        totalGoods = 0;
        if(cartItemMap != null && cartItemMap.size() > 0){
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer, CartItem> cartItemEntry : entries ) {
                CartItem cartItem = cartItemEntry.getValue();
                totalGoods += cartItem.getBuyCount();
            }
        }
        return totalGoods;
    }
}
