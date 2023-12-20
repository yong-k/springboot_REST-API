package com.study.web2.service;

import com.study.web2.exception.DataNotFoundException;
import com.study.web2.mapper.UserMapper;
import com.study.web2.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void createUser(UserVo user) {
        userMapper.createUser(user);
    }

    public List<UserVo> getAllUser() {
        return userMapper.getAllUser();
    }

    public UserVo getUserById(Long id) {
        UserVo user = userMapper.getUserById(id);
        if (user == null)
            throw new DataNotFoundException("Not exist user: id = " + id);
        return user;
    }

    public void updateUser(UserVo user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }
}