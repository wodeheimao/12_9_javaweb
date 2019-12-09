package com.zelin.web.dao;

import com.zelin.web.pojo.Student;
import com.zelin.web.utils.PageResult;

import java.util.List;

public interface StudentDao {
    List<Student> finAll();

    void addStudent(Student student);

    Student finById(int id);

    void updateStudent(Student student);

    void deleteById(int i);

    PageResult<Student> finPageList(int page, int pageSize);

    PageResult<Student> finSearchPageList(int page, int pageSize, String sname, String addr, String cid);
}
