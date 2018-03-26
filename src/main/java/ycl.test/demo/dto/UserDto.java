package ycl.test.demo.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by dr on 2018/3/19.
 */
@Data
public class UserDto {
    private String uid; //用户id
    private String name; //姓名
    private int sex; //性别类型 0 未知   1 男   2 女
    private String address; //地址
    private String phone; //手机号
    private int state; //用户的状态 0 注销   1 有效   2 冻结
    private Date create_time; //用户创建时间
    private Date update_time; //用户更新时间
}
