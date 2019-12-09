package com.zelin.web.servlet;

import com.zelin.web.dao.UserDao;
import com.zelin.web.dao.impl.UserDaoImp;

import com.zelin.web.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/userLogin")
public class UserServlet extends HttpServlet {
    private UserDao userDao;
    @Override
    public void init() throws ServletException {
        userDao = new UserDaoImp();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userDao.login(username,password);
        System.out.println("user = " + user);
        HttpSession session = req.getSession();
        if(user!=null){
            session.setAttribute("user",user);
            resp.sendRedirect(req.getContextPath()+"/student?cmd=pagelist");
        }else{
            session.setAttribute("error","用户名或密码错误");
            resp.sendRedirect(req.getContextPath()+"/login.jsp");
        }

    }
}
