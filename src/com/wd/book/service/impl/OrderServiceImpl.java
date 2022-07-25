package com.wd.book.service.impl;

import com.wd.book.dao.CartItemDAO;
import com.wd.book.dao.OrderDAO;
import com.wd.book.dao.OrderItemDAO;
import com.wd.book.pojo.CartItem;
import com.wd.book.pojo.OrderBean;
import com.wd.book.pojo.OrderItem;
import com.wd.book.pojo.User;
import com.wd.book.service.CartItemService;
import com.wd.book.service.OrderItemService;
import com.wd.book.service.OrderService;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private OrderItemService orderItemService;
    private CartItemService cartItemService;
    @Override
    public boolean addOrderBean(OrderBean orderBean) {
        int dCount = 0;
//        1.訂單表添加一條記錄
        int orderBeanId = orderDAO.addOrderBean(orderBean);//新增完成後，orderBean中的id為t_order表新增列的id
        orderBean.setId(orderBeanId);
//        2.訂單詳情表添加購物車列表中的所有記錄
        //orderBean中的orderItemList是空的，应该根据用户购物车列表项去遍历添加到orderItem中
        User currUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = currUser.getCart().getCartItemMap();
        for (CartItem cartItem : cartItemMap.values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemService.addOrderItem(orderItem);
            //cartItemService.delCartItem(cartItem.getId());
        }
        //List<OrderItem> orderItemList = orderBean.getOrderItemList();
        //for (OrderItem orderItem : orderItemList) {
        //    orderItemService.addOrderItem(orderItem);
        //}
//        3.刪除用戶購物車詳情表中的數據
        for (CartItem cartItem : cartItemMap.values()) {
            cartItemService.delCartItem(cartItem.getId());
            dCount++;
        }
        if(dCount == cartItemMap.size()){
            return true;
        }
        return false;
    }

    @Override
    public List<OrderBean> getOrderList(Integer id) {
        List<OrderBean> orderList = orderDAO.getOrderList(id);
        for (OrderBean orderBean :orderList) {
            int buyTotalCount = getBuyTotalCount(orderBean);
            orderBean.setTotalBuyCount(buyTotalCount);
        }
        return orderList;
    }

    @Override
    public int getBuyTotalCount(OrderBean orderBean) {
        int buyTotalCount = orderDAO.getBuyTotalCount(orderBean);
        return buyTotalCount;
    }
}
