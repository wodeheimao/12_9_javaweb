package com.zelin.web.dao.impl;

import com.zelin.web.dao.ClassesDao;
import com.zelin.web.pojo.Classes;
import com.zelin.web.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ClassesDaoImpl implements ClassesDao {
    private QueryRunner qr;

    public ClassesDaoImpl() {
        qr = new QueryRunner(JdbcUtils.getDataSource());
    }

    //查询所有的班级
    @Override
    public List<Classes> findAll() {
        String sql = "select * from classes";
        try {
            return qr.query(sql,new BeanListHandler<>(Classes.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
