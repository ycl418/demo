package ycl.test.demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by dr on 2018/3/19.
 */
public class DBHelper {
    public static final String url = "jdbc:mysql://localhost/test";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "root";

    public Connection conn = null;
    public PreparedStatement pst = null;

    public DBHelper(String sql){
        try {
            Class.forName(name); // 指定连接类型
            conn = DriverManager.getConnection(url,user,password); //获取连接
            pst = conn.prepareStatement(sql); //准备执行语句
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
