package com.wd.book.controller;

import com.wd.book.pojo.OrderBean;
import com.wd.book.pojo.User;
import com.wd.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OrderController {
    private OrderService orderService;
    public String checkOut(HttpSession session){
        OrderBean orderBean = new OrderBean();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strNowDate = sdf.format(now);
        String[] split = strNowDate.split("-");
        orderBean.setOrderNo(UUID.randomUUID().toString()+"_"+ split.toString());
        orderBean.setOrderDate(now);
        User currUser = (User) session.getAttribute("currUser");
        orderBean.setOrderUser(currUser);
        orderBean.setOrderMoney(currUser.getCart().getTotalPrice());
        orderBean.setOrderStatus(0);
        boolean b = orderService.addOrderBean(orderBean);
        if (b){
            return "redirect:cart.do";
        }
        return "redirect:book.do";
    }
}
