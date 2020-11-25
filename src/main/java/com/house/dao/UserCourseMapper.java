package com.house.dao;

import com.house.pojo.CourseInfo;
import com.house.pojo.UserCourse;
import com.house.pojo.UserInfo;
import com.house.vo.WorkLoadVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Tim
 * @Date 2020/10/21 16:34
 */
@Mapper
public interface UserCourseMapper {

    List<CourseInfo> getCourseInfos(@Param("userId") String userId);

    WorkLoadVO getWorkHours(@Param("courseId") String courseId, @Param("userId")String userId);

    void updateWorkHours(WorkLoadVO workLoadVO);

    void updateDateById(WorkLoadVO workLoadVO);

    List<WorkLoadVO> getAllCourses();

    void updateCoursesInfoByCourseId(WorkLoadVO workLoadVO);

    void insertUserCoursesInfo(WorkLoadVO workLoadVO);

    void insertCourseInfo(WorkLoadVO workLoadVO);

    void deteleCoursesInfo(@Param("courseId") String courseId);

    UserCourse getTeacherWorkLoadByuserIdAndCourseId(@Param("userId")String userId, @Param("courseId")String courseId);

    void deleteTeacherWorkLoad(@Param("userId")String userId, @Param("courseId")String courseId);
}