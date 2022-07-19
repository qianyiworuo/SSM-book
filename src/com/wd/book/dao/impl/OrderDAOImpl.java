package com.wd.book.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.wd.book.dao.OrderDAO;
import com.wd.book.pojo.OrderBean;

public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {
    @Override
    public int addOrderBean(OrderBean orderBean) {
        String sql = "INSERT INTO t_order (id, orderNo, orderDate, orderUser, orderMoney, orderStatus) VALUES(0,?,?,?,?,?,?)";
        int orderBeanId = executeUpdate(sql, orderBean.getOrderNo(), orderBean.getOrderDate(), orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), orderBean.getOrderStatus());
        //此處返回的為INSERT的自增列的id
        return orderBeanId;
    }
}
