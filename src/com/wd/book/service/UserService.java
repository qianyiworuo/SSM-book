package com.wd.book.service;

import com.wd.book.pojo.User;

public interface UserService {
   User login(String uname, String pwd);
   int regist(User user);
   User chkUname(String uname);
}
