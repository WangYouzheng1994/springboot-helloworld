package com.wyz.springboothelloworld.controller;

import com.wyz.springboothelloworld.dao.UserDao;
import com.wyz.springboothelloworld.entity.User;
import com.wyz.springboothelloworld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @RequestMapping("createUser")
    public String createUser(String name, Integer age) {
        this.userService.createUser(name, age);
        return "success";
    }
    @RequestMapping("getUser")
    public User getUser(Integer id) {
        return this.userDao.findById(id).get();
    }
}
