package com.wd.book.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.wd.book.dao.OrderDAO;
import com.wd.book.pojo.OrderBean;
import com.wd.book.pojo.User;

import java.math.BigDecimal;
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
    public List<OrderBean> getOrderList(User user) {
        String sql = "SELECT id, orderNo, orderDate, orderUser, orderMoney, orderStatus FROM t_order WHERE orderUser = ?";
        List<OrderBean> orderBeanList = executeQuery(sql, user.getId());
        return orderBeanList;
    }

    @Override
    public int getBuyTotalCount(OrderBean orderBean) {
        String sql = "SELECT SUM(C.buyCount) AS totalByCount, C.orderBean FROM ";
               sql += " (SELECT A.id, B.buyCount, B.orderBean FROM t_order A INNER JOIN t_order_item B ON A.id = B.orderBean WHERE A.orderUser = ? ) C";
               sql += " WHERE C.orderBean = ? GROUP BY C.orderBean";
        int totCount = ((BigDecimal) executeComplexQuery(sql, orderBean.getOrderUser().getId(), orderBean.getId())[0]).intValue();
        return totCount;
    }
}
