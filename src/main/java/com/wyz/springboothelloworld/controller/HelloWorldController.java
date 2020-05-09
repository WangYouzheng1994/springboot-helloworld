package com.wyz.springboothelloworld.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// @EnableAutoConfiguration    // 自动注入之类的东西
@RestController
// @ComponentScan("com.wyz.springboothelloworld")
public class HelloWorldController {
    // @RequestMapping("/index")
    public String index() {
        return "success";
    }

    @RequestMapping("/getMap")
    public Map<String, Object> getMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("errorCode", "200");
        result.put("errorMsg", "成功");
        return result;
    }
/*
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldController.class, args);
    }*/
}
