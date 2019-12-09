package com.zelin.web.servlet;

import com.zelin.web.dao.ClassesDao;
import com.zelin.web.dao.StudentDao;
import com.zelin.web.dao.impl.ClassesDaoImpl;
import com.zelin.web.dao.impl.StudentDaoImpl;
import com.zelin.web.pojo.Classes;
import com.zelin.web.pojo.Student;
import com.zelin.web.utils.PageResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    StudentDao studentDao;
    ClassesDao classesDao ;
    @Override
    public void init() throws ServletException {
        studentDao = new StudentDaoImpl();
        classesDao = new ClassesDaoImpl();
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        String cmd = req.getParameter("cmd");
        if(cmd!=null && !"".equals(cmd)){
            if("pagetoadd".equals(cmd)){          //跳转添加页面
                this.toadd(req,resp);
            }else if("pageadd".equals(cmd)){            //执行添加操作
                this.add(req,resp);
            }else if("pagetoupdate".equals(cmd)){          //跳转修改页面
                this.toupdate(req,resp);
            }else if("pageupdate".equals(cmd)){            //执行修改操作
                this.update(req,resp);
            }else if("pagedelete".equals(cmd)){            //执行删除操作
                this.delete(req,resp);
            }else if("pagelist".equals(cmd)){            //分页查询操作
                this.pagelist(req,resp);
            }else if("pageSerach".equals(cmd)){            //模糊查询分页查询操作
                this.pageSerach(req,resp);
            }
        }

    }

    //跳转到用户添加页面
    private void toadd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取全部班级
        List<Classes> classes =  classesDao.findAll();
        req.setAttribute("classes" , classes);
        req.getRequestDispatcher("/WEB-INF/student/stu_add.jsp" ).forward(req, resp);
    }

    //执行用户添加操作
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页面信息
        String sname = req.getParameter("sname");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        String addr = req.getParameter("addr");
        String cid = req.getParameter("cid");
        Student student = new Student(sname,sex,new Integer(age),addr);
        student.setCid(new Integer(cid));
        System.out.println(student);
        studentDao.addStudent(student);
        resp.sendRedirect(req.getContextPath() +"/student?cmd=pagelist");

    }

    //跳转到用户修改页面
    private void toupdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("sid");
        //获取学生信息
        Student student = studentDao.finById(Integer.parseInt(sid));
        List<Classes> classes =  classesDao.findAll();
        req.setAttribute("classes" , classes);
        req.setAttribute("student" , student);
        req.getRequestDispatcher("/WEB-INF/student/stu_update.jsp" ).forward(req, resp);
    }

    //执行用户修改操作
    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取页面信息
        String sid = req.getParameter("sid");
        String sname = req.getParameter("sname");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        String addr = req.getParameter("addr");
        String cid = req.getParameter("cid");
        Student student = new Student(new Integer(sid),sname,sex,new Integer(age),addr);
        student.setCid(new Integer(cid));
        System.out.println(student);
        studentDao.updateStudent(student);
        resp.sendRedirect(req.getContextPath() +"/student?cmd=pagelist");

    }
    //跳转到用户删除页面
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("sid");
        //删除学生信息
       studentDao.deleteById(Integer.parseInt(sid));
        resp.sendRedirect(req.getContextPath() +"/student?cmd=pagelist");
    }
    //分页查询
    private void pagelist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获得当前页
        String pageStr = req.getParameter("page");
        int page = new Integer(pageStr == null || pageStr.equals("") ? "1": pageStr );
        int pageSize = 5;
        //得到分页查询的返回的结果集
        PageResult<Student> pageResult = studentDao.finPageList(page,pageSize);
        req.setAttribute("pageResult",pageResult);
        //获取所有的班级信息
        req.setAttribute("classes" , classesDao.findAll());
        req.getRequestDispatcher("/WEB-INF/student/stu_page_list.jsp").forward(req,resp);
    }


    //模糊查询分页查询
    private void pageSerach(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sname = req.getParameter("sname");
        String addr = req.getParameter("addr");
        String cid = req.getParameter("cid");

        //获得当前页
        String pageStr = req.getParameter("page");
        int page = new Integer(pageStr == null || pageStr.equals("") ? "1": pageStr );

        int pageSize = 5;
        //得到模糊查询的分页结果集
        PageResult<Student> pageResult = studentDao.finSearchPageList(page,pageSize,sname,addr,cid);
        req.setAttribute("pageResult",pageResult);
        System.out.println("pageResult = " + pageResult);
        //获取所有的班级信息
        req.setAttribute("classes" , classesDao.findAll());
        req.getRequestDispatcher("/WEB-INF/student/stu_page_list.jsp").forward(req,resp);
    }
}
