package ycl.test.demo.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import ycl.test.demo.service.UserService;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by dr on 2018/3/19.
 */
public class UserServiceByDataSourceImpl implements UserService{
    private static DataSource dataSource;
    private static Connection connection;
    private static PreparedStatement ps;
    private static ResultSet rs;

    @Override
    public void Demo() throws IOException{
        Properties properties = new Properties();
        //从指定的配置文件加载数据到properties中
        properties.load(UserServiceByDataSourceImpl.class.getClassLoader().getResourceAsStream("db.properties"));
        Properties dbProperties =  new Properties();
        /**
         *将前面的jdbc.去掉在重新装入一个Properties中
         */
        for(Object key : properties.keySet()){
            String temp = (String) key;
            if(temp.startsWith("jdbc_")){
                String name = temp.substring(5);
                dbProperties.put(name,properties.getProperty(temp));
            }
        }
        //列出dbProperties中的所有属性值
        dbProperties.list(System.out);

        System.out.println("=========================");

        try {
            //从厂商提供的DataSource实现中加载其对象
            dataSource = (DataSource) Class.forName(properties.getProperty("jdbc_datasource")).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //将dbProperties属性中的内容加入到dataSource对象中
        try {
            BeanUtils.populate(dataSource,new HashMap<>());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ps = connection.prepareStatement("select * from t_user");
            rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    }
