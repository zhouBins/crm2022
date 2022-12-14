package com.bjpowernode.hometalk.web.action;

import com.bjpowernode.hometalk.bean.News;
import com.bjpowernode.hometalk.bean.Room;
import com.bjpowernode.hometalk.bean.User;
import com.bjpowernode.hometalk.utils.DBUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
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

@WebServlet({"/room/create","/room/join","/room/list","/room/send","/room/del","/room/fulsh"})
public class RoomServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();

        if ("/room/create".equals(servletPath)){
            doCreate(request,response);
        }else if ("/room/join".equals(servletPath)){
            doJoin(request,response);
        }else if ("/room/list".equals(servletPath)){
            doList(request,response);
        }else if ("/room/send".equals(servletPath)){
            doSend(request,response);
        }else if ("/room/del".equals(servletPath)){
            doDel(request,response);
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String roomTheme = request.getParameter("roomtheme");
        String username = request.getParameter("username");

        String contextPath = request.getContextPath();

        Connection conn = null;
        PreparedStatement ps = null;
        int count;

        try {
            conn= DBUtil.getConnection();
            String sql = "delete from t_room where roomtheme = ? and houseOwner = ?";

            ps = conn.prepareStatement(sql);

            //4?????????sql??????
            ps.setString(1,roomTheme);
            ps.setString(2,username);

            count = ps.executeUpdate();

            //5????????????????????????
            if(count == 1){

                response.sendRedirect(contextPath+"/room/list");
            }else {
                response.sendRedirect(contextPath+"/error.jsp");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
    }





    private void doSend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //????????????????????????????????????????????? ??????????????????News????????? ?????????????????????????????????
        String usercall = request.getParameter("usercall");
        String roomTheme = request.getParameter("roomtheme");
        String roomChars = request.getParameter("roomchars");

        String newstr = new String(usercall+":"+roomChars);

        String contextPath = request.getContextPath();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count=0;

        try {
            conn= DBUtil.getConnection();
            String sql = "update t_room set roomchars = ? where roomtheme = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1,newstr);
            ps.setString(2,roomTheme);

            //4?????????sql??????
            count = ps.executeUpdate();

            //??????count=1????????????????????? ???????????????????????? ????????????????????????????????????????????????session?????? ???????????????????????????
            if(count == 1){
                //????????????????????????????????????
              /*  HttpSession session = request.getSession();
                news.add(newstr);


                session.setAttribute("news",news);*/

                response.sendRedirect(contextPath+"/news/list");

            }else {
                response.sendRedirect(contextPath+"/error.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }


    }

    private void doList(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        HttpSession session = request.getSession();

        /*String userid =(String) session.getAttribute("userid");*/

        String contextPath = request.getContextPath();

        ArrayList<Room> rooms = new ArrayList<Room>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn= DBUtil.getConnection();
            String sql = "select * from t_room";

            ps = conn.prepareStatement(sql);

            //4?????????sql??????
            rs = ps.executeQuery();

            //5????????????????????????
           while (rs.next()){

                String roomtheme = rs.getString("roomtheme");
                String houseOwner = rs.getString("houseOwner");


                Room room = new Room(roomtheme,null,null,houseOwner);

                rooms.add(room);
            }
            session.setAttribute("roomList",rooms);

            response.sendRedirect(contextPath+"/rooms.jsp");


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
    }

    private void doJoin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //????????????????????? ???????????????????????????????????? ???????????????????????????????????????????????? ?????????????????????
        String roomPW = request.getParameter("roomPW");
        String roomTheme = request.getParameter("roomTheme");

       /* HttpSession session = request.getSession();*/

        /*String userid =(String) session.getAttribute("userid");*/

        String contextPath = request.getContextPath();

       /* ArrayList<Room> rooms = new ArrayList<Room>();*/

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn= DBUtil.getConnection();
            String sql = "select * from t_room where roomtheme = ? and roompw = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1,roomTheme);
            ps.setString(2,roomPW);

            //4?????????sql??????
            rs = ps.executeQuery();

            //5????????????????????????
            if(rs.next()){
                HttpSession session = request.getSession();
                //???roomchars?????????news?????????????????????session????????????????????????????????????????????????????????????
                ArrayList<String> newsList = new ArrayList<>();

                if (rs.getString("roomchars")!= null){
                    newsList.add( rs.getString("roomchars"));
                }

                String houseOwner = rs.getString("houseOwner");
                String roomtheme = rs.getString("roomtheme");

                Room room = new Room(roomTheme,null,null,houseOwner);

                session.setAttribute("newsList",newsList);
                session.setAttribute("room",room);

                //?????????????????????????????? ??????????????????
                response.sendRedirect(contextPath+"/talkroom.jsp");


            }else {
                response.sendRedirect(contextPath+"/roomPWError.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
    }





    private void doCreate(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{

/*????????????????????????????????????????????????????????????????????????????????????????????????????????????session??????*/

        //???????????????????????????
        request.setCharacterEncoding("UTF-8");

        String roomPW= request.getParameter("roompw");
        String roomTheme = request.getParameter("roomtheme");
        String userCall = request.getParameter("usercall");


        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");

        String houseOwner = user.getUsername();



        //????????????????????????????????????null
        if (userCall != null){
            user.setUsercall(userCall);
            session.setAttribute("user",user);
        }


        //1???????????????
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = DBUtil.getConnection();

            //3????????????????????????????????????

            String sql = "insert into t_room (roomtheme,roompw,houseOwner) value (?,?,?)";
            //4?????????sql??????
            ps = conn.prepareStatement(sql);

            ps.setString(1, roomTheme);
            ps.setString(2, roomPW);
            ps.setString(3, houseOwner);


            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {  //6???????????????
            DBUtil.close(conn, ps, null);
        }




        if (count == 1) {

            //?????????room???????????????room????????? ???????????????
            Room room = new Room(roomTheme,roomPW,null,houseOwner);

            ArrayList<String> newsList = new ArrayList<>();

            session.setAttribute("room",room);
            session.setAttribute("newsList",newsList);

//?????????????????????????????????
            response.sendRedirect(request.getContextPath()+"/talkroom.jsp");







        } else {
            //?????????????????????????????????
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }
    }
}
