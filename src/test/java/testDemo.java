import org.junit.Test;
import ycl.test.demo.dto.UserDto;
import ycl.test.demo.service.impl.UserServiceByJdbcImpl;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dr on 2018/3/19.
 */
public class testDemo {
    public UserServiceByJdbcImpl jdbcDemo;

    @Test
    public void testDemo(){
        jdbcDemo = new UserServiceByJdbcImpl();
        jdbcDemo.Demo();
    }

    //测试查询
    @Test
    public void testSelect(){
        jdbcDemo = new UserServiceByJdbcImpl();
        UserDto userDto = new UserDto();
        userDto.setUid("u123");
        ResultSet ret = jdbcDemo.select(userDto);
        try {
            System.out.print("name="+ret.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //测试新增
    @Test
    public void testAdd(){
        jdbcDemo = new UserServiceByJdbcImpl();
        UserDto userDto = new UserDto();
        userDto.setUid("u124");
        try {
            userDto.setName(new String("杨颖".getBytes("ISO-8859-1"),"UTF-8"));
            userDto.setAddress(new String("上海".getBytes("ISO-8859-1"),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        userDto.setSex(2);
        userDto.setPhone("18797929788");
        int i= jdbcDemo.add(userDto);
        System.out.print("i:"+i);
    }

    //测试更新
    @Test
    public void testUpdate(){
        jdbcDemo = new UserServiceByJdbcImpl();
        UserDto userDto = new UserDto();
        userDto.setName("hxm");
        userDto.setAddress("SH");
        userDto.setPhone("13916492388");
        userDto.setUid("u123");
        int i = jdbcDemo.update(userDto);
        System.out.print("i:"+i);
    }

    //测试删除
    @Test
    public void testDel(){
        jdbcDemo = new UserServiceByJdbcImpl();
        UserDto userDto = new UserDto();
        userDto.setUid("u124");
        int i = jdbcDemo.delete(userDto);
        System.out.print("i:"+i);
    }
}
