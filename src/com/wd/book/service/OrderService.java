package com.wd.book.service;

import com.wd.book.pojo.OrderBean;
import com.wd.book.pojo.User;

import java.util.List;

public interface OrderService {
    boolean addOrderBean(OrderBean orderBean);
    List<OrderBean> getOrderList(User user);
    int getBuyTotalCount(OrderBean orderBean);
}
