package ycl.test.demo.service.impl;

import ycl.test.demo.dto.UserDto;
import ycl.test.demo.service.UserService;
import ycl.test.demo.utils.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dr on 2018/3/19.
 */
public class UserServiceByJdbcImpl implements UserService{
    static String sql = null;
    static DBHelper db1 = null;
    static ResultSet ret = null;

    @Override
    public void Demo() {
        sql = "select * from t_user"; //sql语句
        db1 = new DBHelper(sql); // 创建DBHelper对象
        try {
            ret = db1.pst.executeQuery(); // 执行语句,得到结果集
            while (ret.next()){
                String name = ret.getString(3);
                System.out.println("name:"+name);
            }//显示数据
            ret.close();
            db1.close();//关闭连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //查询操作
    public ResultSet select(UserDto userDto){
        ResultSet ret = null;
        String sql = "select * from t_user where uid=?";
        db1 = new DBHelper(sql);
        PreparedStatement pst = db1.pst;
        try {
            pst.setString(1,userDto.getUid());
            ret = pst.executeQuery();
//            ret.close();
//            db1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    //插入操作
    public int add(UserDto userDto){
        int i = 0;
        String sql = "insert into t_user(uid,name,sex,address,phone) values (?,?,?,?,?)";
        db1 = new DBHelper(sql);
        PreparedStatement pst = db1.pst;
        try {
            pst.setString(1,userDto.getUid());
            pst.setString(2,userDto.getName());
            pst.setInt(3,userDto.getSex());
            pst.setString(4,userDto.getAddress());
            pst.setString(5,userDto.getPhone());
            i = pst.executeUpdate();
            pst.close();
            db1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    //更新操作
    public int update(UserDto userDto){
        int i = 0;
        String sql = "update t_user set name=?,address=?,phone=? where uid=?";
        db1 = new DBHelper(sql);
        PreparedStatement pst = db1.pst;
        try {
            pst.setString(1,userDto.getName());
            pst.setString(2,userDto.getAddress());
            pst.setString(3,userDto.getPhone());
            pst.setString(4,userDto.getUid());
            i = pst.executeUpdate();
            pst.close();
            db1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    //删除操作
    public  int delete(UserDto userDto){
        int i = 0;
        String sql = "delete from t_user where uid=?";
        db1 = new DBHelper(sql);
        PreparedStatement pst = db1.pst;
        try {
            pst.setString(1,userDto.getUid());
            i = pst.executeUpdate();
            pst.close();
            db1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
