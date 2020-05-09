package com.wyz.springboothelloworld.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class IndexController {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/indexController")
    public String indexController(Map<String, Object> result) {
        System.out.println("IndexController....index");
        result.put("name", "wangyouzheng");
        result.put("sex", 0);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("zhangsan");
        strings.add("list");
        result.put("userList", strings);
        return "index";
    }

    @ResponseBody
    @RequestMapping("/index")
    public String index() {
        try {
         Thread.sleep(200);
        } catch (Exception e) {
        }
        return "this is " +serverPort;
    }
}