package com.house.service;

import com.house.common.Result;
import com.house.common.StatusCode;
import com.house.dao.UserCourseMapper;
import com.house.dao.UserInfoMapper;
import com.house.dto.LoginUser;
import com.house.pojo.CourseInfo;
import com.house.pojo.UserCourse;
import com.house.pojo.UserInfo;
import com.house.vo.CourseHoursVO;
import com.house.vo.CourseQueryVO;
import com.house.vo.WorkLoadVO;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @Author Tim
 * @Date 2020/10/21 14:13
 */
@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserCourseMapper userCourseMapper;

    public Result loginOnSystem(LoginUser loginUser) {
        UserInfo userInfo = null;
        try {
            if (StringUtils.isEmpty(loginUser.getAccount()) || StringUtils.isEmpty(loginUser.getPassword())) {
                return new Result(false, StatusCode.ERROR,"用戶或者密碼不正确");
            }
            userInfo = userInfoMapper.loginSystemByPdw(loginUser.getAccount(), loginUser.getPassword());
            if (userInfo == null) {
                return new Result(false, StatusCode.ERROR,"无此用户");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(true, StatusCode.SUCCESS,"登录成功", userInfo);
    }


    public Result updateUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            return new Result(true, StatusCode.SUCCESS,"", null);
        }
        userInfoMapper.updateUserInfo(userInfo);
        return new Result(true, StatusCode.SUCCESS, StatusCode.UPDATE_SUCCESS, null);

    }

    public Result getWorkInfo(String userId) {
        List<CourseInfo> courseInfos = userCourseMapper.getCourseInfos(userId);
        return new Result(true, StatusCode.SUCCESS, StatusCode.QUERY_SUCCESS, courseInfos);
    }

    public Result getWorkHours(String courseId, String userId) {
        WorkLoadVO workLoadVO = userCourseMapper.getWorkHours(courseId, userId);
        return new Result(true, StatusCode.SUCCESS, StatusCode.QUERY_SUCCESS, workLoadVO);
    }

    public Result updateWorkHours(WorkLoadVO workLoadVO) {
        if (workLoadVO.getCourseId() == null && workLoadVO.getUserId() == null) {
            return new Result(false, StatusCode.ERROR, "更新数据id为空", null);
        }
        if (workLoadVO.getCourseId() != null && workLoadVO.getCourseHours() != null && workLoadVO.getUserId() != null) {
            userCourseMapper.updateWorkHours(workLoadVO);
        }
        if (workLoadVO.getCourseId() != null && workLoadVO.getCourseDate() != null && workLoadVO.getUserId() != null) {
            userCourseMapper.updateDateById(workLoadVO);
        }
        return new Result(true, StatusCode.SUCCESS, StatusCode.UPDATE_SUCCESS, null);
    }

    public Result updateUserPwd(UserInfo userInfo) {
        if (userInfo.getPwd() == null) {
            return new Result(false, StatusCode.ERROR,"旧密码为空", null);
        }
        UserInfo user = userInfoMapper.loginSystemByPdw(userInfo.getUserId(), userInfo.getPwd());
        if (user == null) {
            return new Result(false, StatusCode.ERROR,"旧密码錯誤", null);
        }
        userInfoMapper.updateUserPwd(userInfo);
        return new Result(true, StatusCode.SUCCESS, StatusCode.UPDATE_SUCCESS, null);
    }

    public Result queryCourses(CourseQueryVO courseQueryVO) {
        CourseHoursVO courseHoursVO = new CourseHoursVO();
        List<WorkLoadVO> workLoadVOS = userInfoMapper.getCourseHourByTimeOrId(courseQueryVO);
        this.setTotalHours(workLoadVOS, courseHoursVO);
        courseHoursVO.setWorkLoadVO(workLoadVOS);
        return new Result(true, StatusCode.SUCCESS, StatusCode.QUERY_SUCCESS, courseHoursVO);
    }

    private void setTotalHours(List<WorkLoadVO> workLoadVOS, CourseHoursVO courseHoursVO) {
        if (!CollectionUtils.isEmpty(workLoadVOS)) {
            courseHoursVO.setTotalHours(workLoadVOS.stream().mapToInt(WorkLoadVO :: getCourseHours).sum());
        }
    }

    public Result getAllCourses() {
        return new Result(true, StatusCode.SUCCESS, StatusCode.QUERY_SUCCESS, userCourseMapper.getAllCourses());
    }

    public Result updateCoursesInfoByCourseId(WorkLoadVO workLoadVO) {
        userCourseMapper.updateCoursesInfoByCourseId(workLoadVO);
        return new Result(true, StatusCode.SUCCESS, StatusCode.UPDATE_SUCCESS, null);
    }

    public Result insertCoursesInfo(WorkLoadVO workLoadVO) {
        userCourseMapper.insertCourseInfo(workLoadVO);
        return new Result(true, StatusCode.SUCCESS, StatusCode.UPDATE_SUCCESS, null);
    }

    public Result deteleCoursesInfo(String courseId) {
        if (courseId == null || Objects.equals("", courseId)) {
            return new Result(false, StatusCode.ERROR,"課程id爲空", null);
        }
        userCourseMapper.deteleCoursesInfo(courseId);
        return new Result(true, StatusCode.SUCCESS, StatusCode.UPDATE_SUCCESS, null);
    }

    public Result getAllUser() {
        List<UserInfo> userInfos = userInfoMapper.getAllUser();
        return new Result(true, StatusCode.SUCCESS, StatusCode.QUERY_SUCCESS, userInfos);
    }

    public Result insertTeacherInfo(UserInfo userInfo) {
        userInfoMapper.insertTeacherInfo(userInfo);
        return new Result(true, StatusCode.SUCCESS, StatusCode.UPDATE_SUCCESS, null);
    }

    public Result updateTeacherInfo(UserInfo userInfo) {
        if (userInfo.getUserId() == null) {
            return new Result(false, StatusCode.ERROR,"用户id为空", null);
        }
        userInfoMapper.updateUserInfo(userInfo);
        return new Result(true, StatusCode.SUCCESS, StatusCode.UPDATE_SUCCESS, null);
    }

    public Result deteleTeacherInfo(String userId) {
        if (userId == null) {
            return new Result(false, StatusCode.ERROR,"用户id为空", null);
        }
        userInfoMapper.deteleTeacherInfoByUserId(userId);
        return new Result(true, StatusCode.SUCCESS, StatusCode.UPDATE_SUCCESS, null);
    }

    public Result getTeacherInfoByName(String userName) {
        if (userName == null || Objects.equals("", userName)) {
            return new Result(false, StatusCode.ERROR,"用户输入为空", null);
        }
        List<WorkLoadVO> workLoadVOS = userInfoMapper.getTeacherInfoByName(userName);
        return new Result(true, StatusCode.SUCCESS, StatusCode.QUERY_SUCCESS, workLoadVOS);
    }

    public Result insertTeacherWorkLoad(WorkLoadVO workLoadVO) {
        if (workLoadVO.getUserId() == null || workLoadVO.getCourseId() == null) {
            return new Result(false, StatusCode.ERROR,"用戶id或者課程id爲空", null);
        }
        userCourseMapper.insertUserCoursesInfo(workLoadVO);
        return new Result(true, StatusCode.SUCCESS, StatusCode.UPDATE_SUCCESS, null);
    }

    public Result deleteTeacherWorkLoad(String userId, String courseId) {
        if (userId == null || courseId == null) {
            return new Result(false, StatusCode.ERROR,"用戶id或者課程id爲空", null);
        }
        UserCourse userCourse = userCourseMapper.getTeacherWorkLoadByuserIdAndCourseId(userId, courseId);
        if (userCourse == null) {
            return new Result(false, StatusCode.ERROR,"无此记录", null);
        }
        userCourseMapper.deleteTeacherWorkLoad(userId, courseId);
        return new Result(true, StatusCode.SUCCESS, StatusCode.UPDATE_SUCCESS, null);
    }
}
