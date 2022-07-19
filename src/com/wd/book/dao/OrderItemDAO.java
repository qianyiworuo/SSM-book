package com.wd.book.dao;

import com.wd.book.pojo.OrderItem;

public interface OrderItemDAO {
    //添加訂單列表
    int addOrderItem(OrderItem orderItem);
}
