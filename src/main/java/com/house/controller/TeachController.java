package com.house.controller;

import com.house.common.Result;
import com.house.dto.LoginUser;
import com.house.pojo.UserInfo;
import com.house.service.UserInfoService;
import com.house.vo.CourseQueryVO;
import com.house.vo.WorkLoadVO;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeachController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/loginSystem", method = RequestMethod.POST)
    public Result login(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        LoginUser loginUser = new LoginUser();
        loginUser.setAccount(userId);
        loginUser.setPassword(password);
        return userInfoService.loginOnSystem(loginUser);
    }

    /**
     * 1.用户登录
     * @param
     * @return
     */
//    @RequestMapping(value = "/loginSystem", method = RequestMethod.GET)
//    public Result loginSystem(@RequestBody LoginUser loginUser){
//        return userInfoService.loginOnSystem(loginUser);
//    }


    /**
     * 2.更新用户信息
     * @param userInfo
     * @return
     */
    @PostMapping("/updateUserInfo")
    public Result updateUserInfo(@RequestBody UserInfo userInfo) {
        return userInfoService.updateUserInfo(userInfo);
    }

    /**
     * 3.查看课程与课程所用的工作量
     * @param userId
     * @return
     */
    @GetMapping(value="/getWorkInfo/{userId}")
    public Result getWorkInfo(@PathVariable String userId){
        return userInfoService.getWorkInfo(userId);
    }

    /**
     * 4.查看单门课程的工作量信息
     * @return
     */
    @GetMapping(value="/getWorkHours/{userId}/{courseId}")
    public Result getWorkHours(@PathVariable(value="courseId",required = false)String courseId,
        @PathVariable(value="userId",required = false)String userId){
        return userInfoService.getWorkHours(courseId, userId);
    }

    /**
     * 5.更新工作量
     * @param workLoadVO
     * @return
     */
    @PostMapping("/updateWorkHours")
    public Result updateWorkHours(@RequestBody WorkLoadVO workLoadVO){
        return userInfoService.updateWorkHours(workLoadVO);
    }

    /**
     * 6.修改密碼
     * @param userInfo
     * @return
     */
    @PostMapping("/updateUserPwd")
    public Result updateUserPwd(@RequestBody UserInfo userInfo){
        return userInfoService.updateUserPwd(userInfo);
    }

    /**
     * 7.查询课时
     * @param courseQueryVO
     * @return
     */
    @GetMapping("/queryCourses")
    public Result queryCourses(@RequestBody CourseQueryVO courseQueryVO){
        return userInfoService.queryCourses(courseQueryVO);
    }

    /**
     * 8.课程管理,查詢所有課程
     * @return
     */
    @GetMapping("/getAllCourses")
    public Result getAllCourses() {
        return userInfoService.getAllCourses();
    }

    /**
     * 9.修改課程信息
     * @param workLoadVO
     * @return
     */
    @PostMapping("/updateCoursesInfo")
    public Result updateCoursesInfo(@RequestBody WorkLoadVO workLoadVO){
        return userInfoService.updateCoursesInfoByCourseId(workLoadVO);
    }

    /**
     * 10.新增課程信息
     * @param workLoadVO
     * @return
     */
    @PostMapping("/insertCoursesInfo")
    public Result insertCoursesInfo(@RequestBody WorkLoadVO workLoadVO){
        return userInfoService.insertCoursesInfo(workLoadVO);
    }

    /**
     * 11.刪除課程
     * @return
     */
    @PostMapping("/deteleCoursesInfo/{courseId}")
    public Result deteleCoursesInfo(@PathVariable String courseId){
        return userInfoService.deteleCoursesInfo(courseId);
    }

    /**
     * 12.查询所有教师信息
     * @return
     */
    @GetMapping("/getAllTeacher")
    public Result getAllUser(){
        return userInfoService.getAllUser();
    }

    /**
     * 13.新增教师
     * @param userInfo
     * @return
     */
    @PostMapping("/insertTeacherInfo")
    public Result insertTeacherInfo(@RequestBody UserInfo userInfo) {
        return userInfoService.insertTeacherInfo(userInfo);
    }

    /**
     * 14.修改教师信息
     * @param userInfo
     * @return
     */
    @PostMapping("/updateTeacherInfo")
    public Result updateTeacherInfo(@RequestBody UserInfo userInfo) {
        return userInfoService.updateTeacherInfo(userInfo);
    }

    /**
     * 15.删除教师信息
     * @param userId
     * @return
     */
    @PostMapping("/deteleTeacherInfo/{userId}")
    public Result deteleTeacherInfo(@PathVariable String userId) {
        return userInfoService.deteleTeacherInfo(userId);
    }

    /**
     * 16.通过教师姓名查询教师课程信息
     * @return
     */
    @GetMapping("/getTeacherInfoByName/{userName}")
    public Result getTeacherInfoByName(@PathVariable String userName){
        return userInfoService.getTeacherInfoByName(userName);
    }

    /**
     * 17.新增教师课程
     * @param workLoadVO
     * @return
     */
    @PostMapping("/insertTeacherWorkLoad")
    public Result insertTeacherWorkLoad(@RequestBody WorkLoadVO workLoadVO) {
        return userInfoService.insertTeacherWorkLoad(workLoadVO);
    }

    /**
     * 18.删除教师课程
     * @return
     */
    @PostMapping("/deleteTeacherWorkLoad/{userId}/{courseId}")
    public Result deleteTeacherWorkLoad(@PathVariable String userId, @PathVariable String courseId) {
        return userInfoService.deleteTeacherWorkLoad(userId, courseId);
    }

}
