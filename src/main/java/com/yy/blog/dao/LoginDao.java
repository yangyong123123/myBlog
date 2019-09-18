package com.yy.blog.dao;

import com.yy.blog.domian.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginDao extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
