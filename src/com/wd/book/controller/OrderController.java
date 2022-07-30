package com.wd.book.controller;

import com.wd.book.pojo.OrderBean;
import com.wd.book.pojo.User;
import com.wd.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private OrderService orderService;
    public String checkOut(HttpSession session){
        OrderBean orderBean = new OrderBean();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strNowDate = sdf.format(now);
        String rpSrt = strNowDate.replace("-", "");
        String reDate = rpSrt.replace(":", "");
        String sNowDate = reDate.replace(" ", "");
//        String[] split = strNowDate.split("-");
//        StringBuffer sb = new StringBuffer();
//        for(int i = 0; i < split.length; i++){
//            sb.append(split[i]);
//        }
//        String strDate = sb.toString();
//        String[] split1 = strDate.split(":");
//        StringBuffer sb1 = new StringBuffer();
//        for(int i = 0; i < split1.length; i++){
//            sb1.append(split[i]);
//        }
        orderBean.setOrderNo(UUID.randomUUID().toString()+"_"+ sNowDate);
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
    public String getOrderList(HttpSession session){
        User user = (User) session.getAttribute("currUser");
        List<OrderBean> orderList = orderService.getOrderList(user);
        session.setAttribute("order", orderList);
        return "order/order";
    }
}
