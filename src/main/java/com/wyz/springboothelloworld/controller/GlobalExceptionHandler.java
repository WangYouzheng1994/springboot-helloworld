package com.wyz.springboothelloworld.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody // 返回json
    public Map<String, Object> resultError() {
        Map<String, Object> result = new HashMap<>();
        result.put("errorCode", "500");
        result.put("errorMsg", "系统错误！");
        return result;
    }
}
