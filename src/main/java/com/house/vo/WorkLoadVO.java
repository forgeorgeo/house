package com.house.vo;

import lombok.Data;

/**
 * @Author Tim
 * @Date 2020/10/21 17:03
 */
@Data
public class WorkLoadVO {

    //用户id
    private String userId;
    //用户名
    private String userName;
    //课程id
    private String courseId;
    //课程名
    private String courseName;
    //课程详细
    private String courseInfo;
    //课程日期
    private String courseDate;
    //工作量
    private Integer courseHours;
}
