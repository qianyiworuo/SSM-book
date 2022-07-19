package com.wd.book.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.wd.book.dao.OrderDAO;
import com.wd.book.dao.OrderItemDAO;
import com.wd.book.pojo.OrderItem;

public class OrderItemImpl extends BaseDAO<OrderItem> implements OrderItemDAO {
    @Override
    public int addOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO t_order_item (id, book, buyCount, orderBean) VALUES (0,?,?,?)";
        int iCount = executeUpdate(sql, orderItem.getBook().getId(), orderItem.getBuyCount(), orderItem.getOrderBean().getId());
        return iCount;
    }
}
