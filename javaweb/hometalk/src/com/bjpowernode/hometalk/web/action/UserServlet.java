package com.bjpowernode.hometalk.web.action;

import com.bjpowernode.hometalk.bean.User;
import com.bjpowernode.hometalk.utils.DBUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//模板类和注解式开发解决类爆炸
//将一个个Servlet转换成方法，通常是一个请求对应一个Servlet，一个Servlet对应一个方法
@WebServlet({"/user/reg","/user/user","/user/edituser","/user/updateuser","/user/editpw","/user/updateuserpw","/user/exit",
        "/user/login","/user/index","/user/feedback","/user/readyroom","/user/talkroom","/user/robot"})
public class UserServlet extends HttpServlet {




    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        String servletPath = request.getServletPath();
        if ("/user/login".equals(servletPath)) {

        doLogin(request, response);

        }

    /*    if (session !=null && session.getAttribute("userid")!= null){
//1'首先要获得浏览器请求的资源路径*/

            //2'依次判断与注解上的资源路径，哪个相等执行哪个方法
            if ("/user/reg".equals(servletPath)){
                doReg(request,response);
            }else if ("/user/user".equals(servletPath)){
                doUser(request,response);
            }else if ("/user/edituser".equals(servletPath)){
                doEditUser(request,response);
            }else if ("/user/updateuser".equals(servletPath)){
                doUpdateUser(request,response);
            }else if ("/user/updateuserpw".equals(servletPath)){
                doUpdateUserPW(request,response);
            }else if ("/user/editpw".equals(servletPath)) {
                doEditPW(request, response);
            }else if ("/user/exit".equals(servletPath)) {
                doExit(request, response);
            }else if ("/user/index".equals(servletPath)) {
                doIndex(request, response);
            }else if ("/user/feedback".equals(servletPath)) {
                doFeedBack(request, response);
            }else if ("/user/readyroom".equals(servletPath)) {
                doReadyRoom(request, response);
            }else if ("/user/talkroom".equals(servletPath)) {
                doTalkRoom(request, response);
            }else if ("/user/robot".equals(servletPath)) {
                doRobot(request, response);
            }
/*

        }else {
            //没有登录就跳到登录页面
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
*/




    }
    //机器人
    private void doRobot(HttpServletRequest request, HttpServletResponse response) {
    }
    //沟通房间页面
    private void doTalkRoom(HttpServletRequest request, HttpServletResponse response) {
    }
    //沟通准备房间
    private void doReadyRoom(HttpServletRequest request, HttpServletResponse response) {
    }
    //反馈
    private void doFeedBack(HttpServletRequest request, HttpServletResponse response) {
    }

    //主页面
    private void doIndex(HttpServletRequest request, HttpServletResponse response) {
    }


    //登录页面
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();



        String userid = request.getParameter("userid");
        String userpassword = request.getParameter("userpassword");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean succes = false;
        User user = null;

        try {
            conn = DBUtil.getConnection();

            String sql = "select * from t_user where userid = ? and userpassword = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, userid);
            ps.setString(2, userpassword);

            rs = ps.executeQuery();

            if (rs.next()) {
                //登录成功
                succes = true;
                String id = rs.getString("userid");
                String name = rs.getString("username");
                String call = rs.getString("usercall");

               user = new User(id,name,userpassword,call);

//                request.setAttribute("username",name);
//                request.setAttribute("usercall",call);






            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);

        }

        if (succes) {
            //成功就获得session对象，没有就创建
            HttpSession session = request.getSession();
            session.setAttribute("userid",userid);
            session.setAttribute("user",user);

            //获得”十天内免登录“对象
            String flag = request.getParameter("flag");
            //判断用户是否勾选
            if ("1".equals(flag)){
                //1表示勾选了 则创建2个cookie对象，一个保存userid，一个保存userpassword
                // 并调用方法设置10天时间的参数
                //然后设置路径 ，因为是登录功能的扩展，所以cookie路径设为项目名的路径
                //然后把设置好的cookie响应到浏览器
                Cookie cookieUserId = new Cookie("userid", userid);
                Cookie cookieUserPW = new Cookie("userpassword", userpassword);

                cookieUserId.setMaxAge(60*60*24*10);
                cookieUserPW.setMaxAge(60*60*24*10);

                cookieUserId.setPath(contextPath);
                cookieUserPW.setPath(contextPath);

                response.addCookie(cookieUserId);
                response.addCookie(cookieUserPW);

                //只要访问这个路径或者它的子路径就会自动把对应路径的cookie请求给服务器

            }


            //重定位到用户个人信息页面
          /*  response.sendRedirect(contextPath+"/user/user");*/
            /*response.sendRedirect(contextPath+"/rooms.jsp");*/
            response.sendRedirect(contextPath+"/room/list");



        }else{
            //失败就跳转到错误页面
            response.sendRedirect(contextPath+"/error.jsp");

        }
    }

    //退出登录
    private void doExit(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        HttpSession session = request.getSession(false);

        Cookie[] cookies = request.getCookies();

        if (session != null){
            session.invalidate();
            //之后跳转到登录页面
            response.sendRedirect(request.getContextPath());
            if (cookies != null) {
                //遍历出所有的cookie 查看是否有用户信息字段的cookie
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if ("userid".equals(name)||"userpassword".equals(name)) {
                        cookie.setMaxAge(0);

                        cookie.setPath(request.getContextPath());

                        response.addCookie(cookie);
                    }
                }
            }

        }



    }




    //编辑用户密码页面

    private void doEditPW(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       /* response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();*/
        String contextPath = request.getContextPath();

        HttpSession session = request.getSession();

        String userid = (String) session.getAttribute("userid");




        /*out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>修改密码</title>");
        out.print("	</head>");

        out.print("	<body>");
        out.print("			<h1>修改密码</h1>");
        out.print("			<hr>");
        out.print("			<form action='"+contextPath+"/user/updateuserpw' method='post'>");
*/



        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "select userid,userpassword from t_user where userid = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, userid);
            //ps.setString(1,user.getUserid()) ;
            //4、执行sql语句
            rs = ps.executeQuery();

            //5、处理查询结果集
            if (rs.next()) {

                String id = rs.getString("userid");
                String password = rs.getString("userpassword");

                response.sendRedirect(contextPath+"/editpw.jsp");

               /* session.setAttribute("userid",userid);
                session.setAttribute("username",username);
                session.setAttribute("usercall",usercall);*/


              /*  out.print("                        当前密码<input type='text'  id='userpassword' /><br>");
                out.print("                        新的密码<input type='text' name='userpassword' id='usernewpassword' /><br>");
                out.print("                        确认密码<input type='text'  id='userpassword' /><br>");
                out.print("					<input type ='hidden' name='userid' value='"+id+"'>");
                out.print("					<input type='submit' id='submit' value='确认修改' /><br>");
                out.print("				</form>");
                out.print("		</div>");
                out.print("	</body>");
                out.print("</html>");*/



            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }


    //编辑提交修改用户信息密码
    private void doUpdateUserPW(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应的内容类型
        String contextPath = request.getContextPath();
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        /*String userid = (String)session.getAttribute("userid");
        String userpassword = (String)session.getAttribute("userpassword");
*/


        String userid = request.getParameter("userid");
        String userpassword = request.getParameter("userpassword");
        //1、注册驱动
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = DBUtil.getConnection();

            //3、获取预编译的数据库对象

            String sql = "update t_user set userpassword = ? where userid = ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1,userpassword);
            ps.setString(2,userid);


            count = ps.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {  //6、释放资源
            DBUtil.close(conn, ps, null);
        }
        if (count == 1) {
            //修改成功跳转到个人信息页面
            response.sendRedirect(contextPath+"/user.jsp");
           /* request.getRequestDispatcher("/user/user").forward(request, response);*/
        } else {
            //修改失败跳转到错误页面
           response.sendRedirect(contextPath+"/error.jsp");
        }
    }
    //编辑提交修改用户信息功能
    private void doUpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        request.setCharacterEncoding("UTF-8");

        String contextPath = request.getContextPath();


        String userid = request.getParameter("userid");
        String username = request.getParameter("username");
        String usercall = request.getParameter("usercall");

            //把获得的数据传回数据库
            Connection conn = null;
            PreparedStatement ps = null;
            int count = 0;
            //获得数据库操作对象
            try {
                conn = DBUtil.getConnection();
                String sql = "update t_user set username = ?,usercall = ? where userid = ?";
                ps = conn.prepareStatement(sql);

                ps.setString(1,username);
                ps.setString(2,usercall);
                ps.setString(3,userid);

                count = ps.executeUpdate();





            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                DBUtil.close(conn,ps,null);
            }

            if(count == 1){//更新成功则跳转到用户信息页面
                session.setAttribute("userid",userid);
                session.setAttribute("username",username);
                session.setAttribute("usercall",usercall);

                /*request.getRequestDispatcher("/user/user").forward(request,response);*/
                response.sendRedirect(contextPath+"/user.jsp");
            }else {//更新失败，则跳转到异常页面
                response.sendRedirect(contextPath+"/error.jsp");
            }

        }



    //编辑用户信息页面
    private void doEditUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*response.setContentType("text/html;charset=UTF-8");*/
       /* PrintWriter out = response.getWriter();*/
        HttpSession session = request.getSession();
        String contextPath = request.getContextPath();

        String userid = (String)session.getAttribute("userid");


/*
 String userid = request.getParameter("userid");
        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>修改个人信息</title>");
        out.print("	</head>");

        out.print("	<body>");
        out.print("			<h1>修改个人信息</h1>");
        out.print("			<hr>");
        out.print("			<form action='"+contextPath+"/user/updateuser' method='post'>");*/




        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "select userid,username,usercall from t_user where userid = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1, userid);
            //4、执行sql语句
            rs = ps.executeQuery();

            //5、处理查询结果集
            if (rs.next()) {

                String id = rs.getString("userid");
                String name = rs.getString("username");
                String call = rs.getString("usercall");

                session.setAttribute("userid",id);
                session.setAttribute("username",name);
                session.setAttribute("usercall",call);

                response.sendRedirect(contextPath+"/edituser.jsp");




               /* out.print("	用户ID<input type='text' name='userid' id='userid' value='"+id+"' readonly/><br>");
                out.print("	用户昵称<input type='text' name='username' id='username' value='"+name+"' /><br>");
                out.print("	默认职称<input type='text'  id='usercall' value='"+call+"' readonly/><br>");*/

            }

          /*  out.print("		<input type='radio' name='usercall' value='父亲' />父亲");
            out.print("        <input type='radio' name='usercall' value='母亲' />母亲");
            out.print("        <input type='radio' name='usercall' value='女儿' />女儿");
            out.print("        <input type='radio' name='usercall' value='儿子' />儿子");
            out.print("        <input type='radio' name='usercall' value='妻子' />妻子");
            out.print("        <input type='radio' name='usercall' value='丈夫' />丈夫");
            out.print("        <input type='radio' name='usercall' value='男朋友' />男朋友");
            out.print("        <input type='radio' name='usercall' value='女朋友' />女朋友");
            out.print("        <input type='radio' name='usercall' value='朋友' />朋友");
            out.print("        <input type='radio' name='usercall' value='哥哥' />哥哥");
            out.print("        <input type='radio' name='usercall' value='姐姐' />姐姐");
            out.print("        <input type='radio' name='usercall' value='弟弟' />弟弟");
            out.print("        <input type='radio' name='usercall' value='妹妹' />妹妹");
            out.print("        <!--<input type='text' name='homename'  />其他-->");
            out.print("		<input type='submit' id='submit' value='修改' /><br>");*/

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }
    //用户信息
    private void doUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();*/

        HttpSession session = request.getSession();

        String userid =(String) session.getAttribute("userid");


        String contextPath = request.getContextPath();
       /* out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>修改个人信息</title>");
        out.print("	</head>");

        out.print("	<body>");
        out.print("			<h1>修改个人信息</h1>");
        out.print("			<hr>");
        out.print("			<table border='1px' align='center' width='50%'>");
        out.print("				<tr>");
        out.print("					<th>用户ID</th>");
        out.print("					<th>用户昵称</th>");
        out.print("					<th>默认家庭职称</th>");
        out.print("				</tr>");
*/


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn= DBUtil.getConnection();
            String sql = "select userid,username,usercall from t_user where userid = ?";

            ps = conn.prepareStatement(sql);

            ps.setString(1,userid);
            //4、执行sql语句
            rs = ps.executeQuery();

            //5、处理查询结果集
            if (rs.next()){

                String id = rs.getString("userid");
                String name = rs.getString("username");
                String call = rs.getString("usercall");

//                request.setAttribute("username",name);
//                request.setAttribute("usercall",call);
                session.setAttribute("userid",id);
                session.setAttribute("username",name);
                session.setAttribute("usercall",call);


               /* out.print("				<tr>");
                out.print("					<td>"+id+"</td>");
                out.print("					<td>"+name+"</td>");
                out.print("					<td>"+call+"</td>");
                out.print("				</tr>");*/

            }
            response.sendRedirect(contextPath+"/user.jsp");


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }


        /*out.print("			</table>");

        out.print("	<a href = '"+contextPath+"/user/edituser?userid="+session.getAttribute("userid")+"'>");
        out.print("			<input type='button' value='编辑信息'/><br>");
        out.print("</a>");

        out.print("<a href = '"+contextPath+"/user/editpw?userid="+session.getAttribute("userid")+"'>");
        out.print("			<input type='button' value='修改密码'/><br>");
        out.print("</a>");

        out.print("<a href = '"+contextPath+"/user/exit'>");
        out.print("			<input type='button' value='退出账号'/><br>");
        out.print("</a>");

        out.print("		</div>");
        out.print("	</body>");
        out.print("</html>");*/

    }


    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    //用户注册
    private void doReg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String contextPath = request.getContextPath();
        //设置响应的内容类型

                request.setCharacterEncoding("UTF-8");

                String userid = request.getParameter("userid");
                String username = request.getParameter("username");
                String userpassword = request.getParameter("userpassword");
                String usercall = request.getParameter("usercall");

                //将注册的信息封装到User对象中，并且将该对象设置到应用域中，ServletContext
                User user = new User(userid,username,userpassword,usercall);

                ServletConfig config = getServletConfig();
                ServletContext context = config.getServletContext();
                context.setAttribute("user",user);


                //1、注册驱动
                Connection conn = null;
                PreparedStatement ps = null;
                int count = 0;

                try {
                    conn = DBUtil.getConnection();

                    //3、获取预编译的数据库对象

                    String sql = "insert into t_user (userid,username,userpassword,usercall) value (?,?,?,?)";
                    //4、执行sql语句
                    ps = conn.prepareStatement(sql);

                    ps.setString(1, userid);
                    ps.setString(2, username);
                    ps.setString(3, userpassword);
                    ps.setString(4, usercall);

                    count = ps.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {  //6、释放资源
                    DBUtil.close(conn, ps, null);
                }
                if (count == 1) {
                    //注册成功跳转到个人信息页面
                    request.getRequestDispatcher("/user/user").forward(request, response);
                } else {
                    //注册失败跳转到错误页面
                    response.sendRedirect(contextPath+"/error.jsp");
                }
            }
        }