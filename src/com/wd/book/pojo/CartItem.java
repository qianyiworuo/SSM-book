package com.wd.book.pojo;

import java.math.BigDecimal;

//我们应该还需要设计一个Cart类，代表购物车这个实体
public class CartItem {
    private Integer id ;
    private Book book ;
    private Integer buyCount ;
    private User userBean ;
    private Double subTotal;

    public CartItem(){}

    public CartItem(Book book, Integer buyCount, User userBean) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
    }

    public CartItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getSubTotal() {
        BigDecimal bigDecimalPrice = new BigDecimal(String.valueOf(book.getPrice()));
        BigDecimal bigDecimalCount = new BigDecimal(String.valueOf(buyCount));
        BigDecimal multiply = bigDecimalCount.multiply(bigDecimalPrice);
        double subTotal = multiply.doubleValue();
        setSubTotal(subTotal);
        return subTotal;
    }
}
