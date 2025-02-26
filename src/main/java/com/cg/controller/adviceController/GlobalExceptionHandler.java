package com.cg.controller.adviceController;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public SaResult handleNotLoginException(NotLoginException e, HttpServletResponse response) {
        e.printStackTrace();
        response.setStatus(401);
        return SaResult.error("请先登录");
    }
}