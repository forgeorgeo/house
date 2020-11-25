package com.house.pojo;

/**
 * @Author Tim
 * @Date 2020/10/21 16:34
 */

import lombok.Data;

/**
    * 用户课程关系表
    */
@Data
public class UserCourse {

    private Integer id;

    /**
    * 用户id
    */
    private Integer userId;

    /**
    * 课程id
    */
    private Integer courseId;

    /**
    * 用了几个课时
    */
    private Integer hour;

}