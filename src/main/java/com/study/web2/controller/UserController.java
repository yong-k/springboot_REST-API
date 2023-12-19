package com.study.web2.controller;

import com.study.web2.service.UserService;
import com.study.web2.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public void registerUser(@RequestBody UserVo user) {
        userService.registerUser(user);
    }

    @GetMapping("/user")
    public List<UserVo> findAll() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public UserVo findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserVo user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}