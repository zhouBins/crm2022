package com.bjpowernode.hometalk.utils;

import java.sql.*;
import java.util.ResourceBundle;

//JDBC工具类
public class DBUtil {

    //静态变量：在类加载时执行；且执行的顺序的至上而下
    private static ResourceBundle bundle= ResourceBundle.getBundle("resources.jdbc");
    private static String driver= bundle.getString("driver");
    private static String url=bundle.getString("url");
    private static String root= bundle.getString("root");
    private static String password= bundle.getString("password");

    static{
        //注册驱动只执行一次就行了，所以把它放到静态代码块中
        try {
            //driver是连接数据库的驱动，不能写死。因为以后可能还要连接Oracle数据库
            //如果连接Oracle数据库的时候，还需要修改java代码，显然违背了OCP开闭原则
            //OCP开闭原则：对扩展开发，对修改关闭。（在进行功能扩展的时候。不需要修改java源代码）
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //获取数据库连接对象
    public static Connection getConnection()throws SQLException{
        //获取连接
        Connection conn  = DriverManager.getConnection(url,root,password);
        return conn;
    }

    //释放资源
    public static void close(Connection conn, Statement ps ,ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }




    }




}
