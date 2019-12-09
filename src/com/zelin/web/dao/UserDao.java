package com.zelin.web.dao;

import com.zelin.web.pojo.User;

public interface UserDao {
    User login(String username, String password);
}
