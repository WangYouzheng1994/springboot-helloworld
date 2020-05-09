package com.wyz.springboothelloworld.dao;

import com.wyz.springboothelloworld.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
