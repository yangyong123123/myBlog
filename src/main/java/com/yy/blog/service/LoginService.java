package com.yy.blog.service;

import com.yy.blog.dao.LoginDao;
import com.yy.blog.domian.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;


@Service
@Transactional
public class LoginService {
    @Autowired
    LoginDao loginDao;

    public User login(String username, String password) {
        User user = loginDao.findByUsername(username);
        if (user == null){
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

}
