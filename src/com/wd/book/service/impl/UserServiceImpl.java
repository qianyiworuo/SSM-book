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
}
