package com.house.dao;

import com.house.pojo.UserInfo;
import com.house.vo.CourseQueryVO;
import com.house.vo.WorkLoadVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Tim
 * @Date 2020/10/21 14:08
 */
@Mapper
public interface UserInfoMapper {

    UserInfo loginSystemByPdw(@Param("userId") String userId, @Param("password") String password);

    void updateUserInfo(UserInfo userInfo);

    void updateUserPwd(UserInfo userInfo);

    List<WorkLoadVO> getCourseHourByTimeOrId(CourseQueryVO courseQueryVO);

    List<UserInfo> getAllUser();

    void insertTeacherInfo(UserInfo userInfo);

    void deteleTeacherInfoByUserId(@Param("userId") String userId);

    List<WorkLoadVO> getTeacherInfoByName(@Param("userName") String userName);
}