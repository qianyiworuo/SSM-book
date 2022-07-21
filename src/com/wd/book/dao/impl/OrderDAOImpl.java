package com.wd.book.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.wd.book.dao.OrderDAO;
import com.wd.book.pojo.OrderBean;

import java.util.List;

public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {
    @Override
    public int addOrderBean(OrderBean orderBean) {
        String sql = "INSERT INTO t_order (id, orderNo, orderDate, orderUser, orderMoney, orderStatus) VALUES(0,?,?,?,?,?)";
        int orderBeanId = executeUpdate(sql, orderBean.getOrderNo(), orderBean.getOrderDate(), orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), orderBean.getOrderStatus());
        //此處返回的為INSERT的自增列的id
        return orderBeanId;
    }

    @Override
    public List<OrderBean> getOrderList(Integer id) {
        String sql = "SELECT id, orderNo, orderDate, orderUser, orderMoney, orderStatus FROM t_order WHERE orderUser = ?";
        List<OrderBean> orderBeanList = executeQuery(sql, id);
        return orderBeanList;
    }
}
