package com.house.config;

import com.house.controller.MyController1;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(assignableTypes = {MyController1.class})
@Slf4j
public class GlobalExceptionHandler1 {

    /**
     * 处理 Exception 异常
     *
     * @param httpServletRequest httpServletRequest
     * @param e                  异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(HttpServletRequest httpServletRequest, Exception e) {
        log.error(e.getMessage());
        return "GlobalExceptionHandler1 服务错误";
    }
}