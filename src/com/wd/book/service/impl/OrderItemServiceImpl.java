package com.wd.book.service.impl;

import com.wd.book.dao.OrderItemDAO;
import com.wd.book.pojo.OrderItem;
import com.wd.book.service.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemDAO orderItemDAO;
    @Override
    public int addOrderItem(OrderItem orderItem) {
        int addOrderItemId = orderItemDAO.addOrderItem(orderItem);
        return addOrderItemId;
    }
}
