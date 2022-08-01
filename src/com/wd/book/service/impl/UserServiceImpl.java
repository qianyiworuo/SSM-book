package com.wd.book.service.impl;

import com.wd.book.dao.UserDAO;
import com.wd.book.pojo.User;
import com.wd.book.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    @Override
    public User login(String uname, String pwd) {
        User user = userDAO.getUser(uname, pwd);
        return user;
    }

    @Override
    public int regist(User user) {
        int iCount = userDAO.addUser(user);
        return iCount;
    }

    @Override
    public User chkUname(String uname) {
        User user = userDAO.getByUname(uname);
        return user;
    }
}
