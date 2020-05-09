package com.wyz.springboothelloworld.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// @EnableAutoConfiguration    // 自动注入之类的东西
// @ComponentScan("com.wyz.springboothelloworld")
// @EnableJpaRepositories("com.wyz.springboothelloworld.dao") // jpa包扫描
// @EntityScan("com.wyz.springboothelloworld.entity") // 配合jpa的实体扫描
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
