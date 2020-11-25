package com.house.config;

import com.house.common.ResponseMessages;
import com.house.controller.Controller1;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice(assignableTypes = {Controller1.class}) //只对Controller1文件做异常处理
//@ControllerAdvice(basePackages = {"cn.myz.demo.controller"}) //指定一个或多个包，这些包及其子包下的所有 Controller 都被该 @ControllerAdvice 管理。
//@ControllerAdvice(basePackageClasses = {MyController1.class}) //指定一个或多个 Controller 类，这些类所属的包及其子包下的所有 Controller 都被该 @ControllerAdvice 管理。
//@ControllerAdvice(assignableTypes = {MyController1.class}) //指定一个或多个 Controller 类，这些类被该 @ControllerAdvice 管理
//@ControllerAdvice(annotations = {RestController.class})  //指定一个或多个注解，被这些注解所标记的 Controller 会被该 @ControllerAdvice 管理。
public class GlobalExceptionHandler {

    //全局异常处理
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseMessages<?> errorHandler(Exception e) {
        return new ResponseMessages<>(10012, e.getMessage() + "exep-- 服务错误", false);
    }

}
