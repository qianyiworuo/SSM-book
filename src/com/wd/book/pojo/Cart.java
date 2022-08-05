package com.wd.book.pojo;

import java.math.BigDecimal;
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
//                totalPrice += cartItem.getBook().getPrice() * cartItem.getBuyCount();
//                BigDecimal bigDecimalPrice = new BigDecimal(String.valueOf(cartItem.getBook().getPrice()));
//                BigDecimal bigDecimalCount = new BigDecimal(String.valueOf(cartItem.getBuyCount()));
                  BigDecimal bigDecimalTotal = new BigDecimal(String.valueOf(totalPrice));
//                BigDecimal multiply = bigDecimalCount.multiply(bigDecimalPrice);
//                BigDecimal total = bigDecimalTotal.add(multiply);
                Double subTotal = cartItem.getSubTotal();
                BigDecimal bigDecimalSubTotal = new BigDecimal(String.valueOf(subTotal));
                BigDecimal total = bigDecimalTotal.add(bigDecimalSubTotal);
                totalPrice = total.doubleValue();
            }
            setTotalPrice(totalPrice);
        }
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalCount() {
        totalCount = 0;
        if(cartItemMap != null && cartItemMap.size() > 0){
            totalCount = cartItemMap.size();
        }
        setTotalCount(totalCount);
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
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
        setTotalGoods(totalGoods);
        return totalGoods;
    }

    public void setTotalGoods(Integer totalGoods) {
        this.totalGoods = totalGoods;
    }
}
