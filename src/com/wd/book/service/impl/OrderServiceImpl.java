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
    public int addOrderBean(OrderBean orderBean) {

//        1.訂單表添加一條記錄
        int orderBeanId = orderDAO.addOrderBean(orderBean);//新增完成後，orderBean中的id為t_order表新增列的id
        orderBean.setId(orderBeanId);
//        2.訂單詳情表添加購物車列表中的所有記錄
        List<OrderItem> orderItemList = orderBean.getOrderItemList();
        for (OrderItem orderItem : orderItemList) {
            orderItemService.addOrderItem(orderItem);
        }
//        3.刪除用戶購物車詳情表中的數據
        User currUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = currUser.getCart().getCartItemMap();
        for (CartItem cartItem : cartItemMap.values()) {
            cartItemService.delCartItem(cartItem.getId());
        }

        return 0;
    }
}
