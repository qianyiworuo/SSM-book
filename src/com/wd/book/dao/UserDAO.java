package com.wd.book.dao;

import com.wd.book.pojo.User;

public interface UserDAO {
    User getUser(String uname, String pwd);
    int addUser(User user);
}
