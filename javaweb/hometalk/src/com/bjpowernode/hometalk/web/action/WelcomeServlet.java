package com.bjpowernode.hometalk.web.action;

import com.bjpowernode.hometalk.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //大体思路：无非两种结果，有保存到有正确的用户信息的cookie让其直接跳转到用户个人信息页面并创建会话
        //没有则跳转到登录页面
        String userid = null;
        String userpassword = null;
        //首先获得用户浏览器上请求中的cookie集合
        Cookie[] cookies = request.getCookies();
        //cookie数组可能为空 如果不为空则接着往下 为空则重定向到登录页面
        if (cookies != null) {
            //遍历出所有的cookie 查看是否有用户信息字段的cookie
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("userid".equals(name)) {
                    userid = cookie.getValue();
                } else if ("userpassword".equals(name)) {
                    userpassword = cookie.getValue();
                }
            }

            //遍历完cookie后 先判断id和密码是否为空 不为空继续往下 否则重定位到登录页面
            if (userid !=null&&userpassword!= null){
                //不为空就判断是否和数据库中的一致 一致则重定位到用户个人信息页面 否则重定位到登录页面
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;

                try {
                    conn=DBUtil.getConnection();
                    //查什么不重要 能查到数据才重要 能查到表示id和密码正确
                    String sql = "select * from t_user where userid = ? and userpassword = ?";

                    ps = conn.prepareStatement(sql);

                    ps.setString(1,userid);
                    ps.setString(2,userpassword);

                    rs = ps.executeQuery();

                    if (rs.next()){
                        //成功就获得session对象，没有就创建
                        HttpSession session = request.getSession();
                        session.setAttribute("userid",userid);
                        //查询到了就表示id和密码是正确的就跳转到用户个人信息页面
                        response.sendRedirect(request.getContextPath()+"/user/user");


                    }else {
                        //没有查询到就说明id或者密码不对
                        response.sendRedirect(request.getContextPath()+"/index.jsp");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    DBUtil.close(conn,ps,rs);
                }
            }else {
                //如果Cookie数组里没有id和password信息 则表示没有对应的信息 所以重定位到登录页面
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }

        }else{//cookie数组可能为空 如果不为空则接着往下 为空则重定向到登录页面
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }
}