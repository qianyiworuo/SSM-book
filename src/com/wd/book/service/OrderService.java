package com.wd.book.service;

import com.wd.book.pojo.OrderBean;

import java.util.List;

public interface OrderService {
    boolean addOrderBean(OrderBean orderBean);
    List<OrderBean> getOrderList(Integer id);
    int getBuyTotalCount(OrderBean orderBean);
}
