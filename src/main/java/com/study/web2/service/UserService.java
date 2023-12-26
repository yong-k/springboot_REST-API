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
    private UserMapper userMapper;

    public void createUser(UserVo user) {
        userMapper.createUser(user);
    }

    public List<UserVo> getAllUser(int pageNum, int numOfRows, String username, String email) {
        pageNum = numOfRows * (pageNum - 1);
        return userMapper.getAllUser(pageNum, numOfRows, username, email);
    }

    public int countUser(String username, String email) {
        return userMapper.countUser(username, email);
    }

    public UserVo getUserById(Long id) {
        UserVo user = userMapper.getUserById(id);
        if (user == null)
            throw new DataNotFoundException("Not exist user: id = " + id);
        return user;
    }

    public void updateUser(UserVo user) {
        int result = userMapper.updateUser(user);
        if (result < 1)
            throw new DataNotFoundException("[UPDATE fail] Not exist user: id = " + user.getId());
    }

    public void deleteUser(Long id) {
        int result = userMapper.deleteUser(id);
        if (result < 1)
            throw new DataNotFoundException("[DELETE fail] Not exist user: id = " + id);
    }
}