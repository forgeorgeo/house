package com.house.pojo;

/**
 * @Author Tim
 * @Date 2020/10/21 14:08
 */

import lombok.Data;

/**
    * 用户表
    */
@Data
public class UserInfo {
    private Integer id;

    //用户id
    private String userId;

    /**
    * 密码
    */
    private String pwd;

    /**
    * 用户姓名
    */
    private String userName;

    /**
    * 用户工号
    */
    private String userNumber;

    /**
    * 用户所属学院
    */
    private String userCollege;

    /**
    * 用户年龄
    */
    private String userAge;

    /**
    * 用户电话
    */
    private String userTel;

    /**
    * 用户性别
    */
    private String userSex;

    /**
    * 用户邮箱
    */
    private String userEmail;

    /**
    * 用户类型 0：教师  1：管理员
    */
    private Integer type;

    private String newPwd;

}