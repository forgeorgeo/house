package com.house.pojo;

/**
 * @Author Tim
 * @Date 2020/10/21 16:33
 */

import lombok.Data;

/**
    * 课程表
    */
@Data
public class CourseInfo {
    /**
    * 课程id
    */
    private Integer id;

    /**
    * 课程名
    */
    private String courseName;

    /**
    * 课程信息
    */
    private String courseInfo;

}