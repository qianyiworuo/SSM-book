package com.wd.book.dao.impl;

import com.atguigu.myssm.basedao.BaseDAO;
import com.wd.book.dao.UserDAO;
import com.wd.book.pojo.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String uname, String pwd) {
        String sql = "SELECT id, uname, pwd, email, role FROM t_user WHERE uname like ? AND pwd like ? ";
        User user = load(sql, uname, pwd);
        return user;
    }
}
