package com.wd.book.dao;

import com.wd.book.pojo.OrderBean;

import java.util.List;

public interface OrderDAO {
    //添加訂單
    int addOrderBean(OrderBean orderBean);
    //通过userId获取订单列表
    List<OrderBean> getOrderList(Integer id);
    //获取订单商品总数量
    int getBuyTotalCount(OrderBean orderBean);
}
