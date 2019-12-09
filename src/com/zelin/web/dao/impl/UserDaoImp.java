package com.zelin.web.dao.impl;

import com.zelin.web.dao.UserDao;
import com.zelin.web.pojo.User;
import com.zelin.web.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImp implements UserDao {
    private QueryRunner qr;

    public UserDaoImp() {
        qr = new QueryRunner(JdbcUtils.getDataSource());
    }

    @Override
    public User login(String username, String password) {
        String sql = "select * from tb_user where username = ? and password = ?";
        try {
            return qr.query(sql, new BeanHandler<>(User.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("sql = " + sql);
        return  null;
    }
}
