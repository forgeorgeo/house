package com.house.pojo;

import java.util.Date;

/**
 * @Author Tim
 * @Date 2020/10/21 16:34
 */

/**
    * 用户课时关系表
    */
public class UserTime {
    private Integer id;

    /**
    * 课程id
    */
    private Integer userCourseId;

    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserCourseId() {
        return userCourseId;
    }

    public void setUserCourseId(Integer userCourseId) {
        this.userCourseId = userCourseId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}