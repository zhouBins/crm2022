package com.bjpowernode.hometalk.web.action;

import com.bjpowernode.hometalk.bean.Room;
import com.bjpowernode.hometalk.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/news/list")
public class NewsServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String servletPath = request.getServletPath();

        if ("/news/list".equals(servletPath)){
            doList(request,response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String contextPath = request.getContextPath();

       Room room = (Room)session.getAttribute("room");
        String roomTheme = room.getRoomTheme();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn= DBUtil.getConnection();
            String sql = "select * from t_room where roomtheme = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1,roomTheme);

            //4、执行sql语句


            rs = ps.executeQuery();

            //5、处理查询结果集
            if(rs.next()){

                String roomchars = rs.getString("roomchars");
                //把消息封装到集合里 再设置到会话域中
                ArrayList<String> newsList = (ArrayList<String>)session.getAttribute("newsList");

                newsList.add(roomchars);

              /*  for (String s : newsList) {
                    System.out.println(s);
                }*/

                session.setAttribute("newsList",newsList);

                response.sendRedirect(contextPath+"/talkroom.jsp");

            }else {
                response.sendRedirect(contextPath+"/error.jsp");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
    }

}
