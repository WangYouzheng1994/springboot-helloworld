package com.wyz.springboothelloworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void createUser(String name, Integer age) {
        System.out.println("createUser");
        jdbcTemplate.update("insert into user values(null, ?, ?);", name, age);
        System.out.println("createUser success");
    }
}
