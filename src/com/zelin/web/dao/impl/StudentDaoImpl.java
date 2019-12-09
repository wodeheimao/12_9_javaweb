package com.zelin.web.dao.impl;

import com.zelin.web.dao.StudentDao;
import com.zelin.web.pojo.Student;
import com.zelin.web.utils.JdbcUtils;
import com.zelin.web.utils.PageResult;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private QueryRunner qr;

    public StudentDaoImpl() {
        qr = new QueryRunner(JdbcUtils.getDataSource());
    }

    //查询所有学生
    @Override
    public List<Student> finAll() {
        String sql = "select st.*,cname from student st , classes ct where st.cid = ct.cid order by st.sid";
        try {
            return qr.query(sql, new BeanListHandler<>(Student.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addStudent(Student student) {
        String sql = "insert into student values(null,?,?,?,?,?)";
        try {
            qr.update(sql, student.getSname(), student.getSex(), student.getAge(), student.getAddr(), student.getCid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //按学生id查找学生
    @Override
    public Student finById(int id) {
        String sql = "select st.*,cname from student st , classes ct where st.cid = ct.cid and st.sid = ?";
        try {
            return qr.query(sql, id, new BeanHandler<>(Student.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //修改学生
    @Override
    public void updateStudent(Student student) {
        String sql = "update student set sname=?, sex=?, age=?, addr=?, cid=? where sid = ? ";
        try {
            qr.update(sql, student.getSname(), student.getSex(), student.getAge(), student.getAddr(), student.getCid(), student.getSid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //删除学生
    @Override
    public void deleteById(int id) {
        String sql = "delete from student where sid = ?";
        try {
            qr.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //分页查询
    @Override
    public PageResult<Student> finPageList(int page, int pageSize) {
        //查询当前页学生
        List<Student> rows = findStudents(page, pageSize);
        //学生总数
        long total = countStudents();
        //页面总数
        long totalPages = (long) Math.ceil(total / (double) pageSize);
        return new PageResult<Student>(rows, page, totalPages, total);
    }

    //模糊分页查询
    @Override
    public PageResult<Student> finSearchPageList(int page, int pageSize, String sname, String addr, String cid) {
        //模糊查询当前页学生
        List<Student> rows = findSearchStudents(page, pageSize, sname, addr, cid);
        //模糊学生总数
        long total = countSearchStudents(sname, addr, cid);
        //模糊查询页面总数
        long totalPages = (long) Math.ceil(total / (double) pageSize);
        return new PageResult<Student>(rows, page, totalPages, total);
    }

    //模糊分页的总数
    private long countSearchStudents(String sname, String addr, String cid) {
        String sql = "select count(*) from student st,classes c where c.cid=st.cid ";
        if (sname != null && !"".equals(sname)) {
            sql += "and st.sname like '%" + sname + "%'";
        }
        if (addr != null && !"".equals(addr)) {
            sql += "and st.addr like '%" + addr + "%'";
        }
        if (!"0".equals(cid)) {
            sql += "and st.cid = " + cid;
        }
        System.out.println("sql(得到模糊分页的总数) = " + sql);
        try {
            return (long) qr.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //模糊当前分页的结果集
    private List<Student> findSearchStudents(int page, int pageSize, String sname, String addr, String cid) {
        String sql = "select st.*,cname from student st,classes c where c.cid=st.cid ";
        if (sname != null && !"".equals(sname)) {
            sql += "and st.sname like '%" + sname + "%'";
        }
        if (addr != null && !"".equals(addr)) {
            sql += "and st.addr like '%" + addr + "%'";
        }
        if (!"0".equals(cid)) {
            sql += "and st.cid = " + cid;
        }
        sql += " limit ?,?";
        System.out.println("sql(得到模糊分页当前结果集) = " + sql);
        try {
            return qr.query(sql, new BeanListHandler<>(Student.class), (page - 1) * pageSize, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //普通分页求总数
    private long countStudents() {
        String sql = "select count(*) from student";
        try {
            return (long) qr.query(sql, new ScalarHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //普通分页当前集合
    private List<Student> findStudents(int page, int pageSize) {
        String sql = "select st.*,cname from student st,classes c where c.cid=st.cid limit ?,?";
        try {
            return qr.query(sql, new BeanListHandler<>(Student.class), (page - 1) * pageSize, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
