package com.study.web2.controller;

import com.study.web2.exception.DataNotFoundException;
import com.study.web2.response.BaseResponse;
import com.study.web2.service.UserService;
import com.study.web2.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public BaseResponse<String> createUser(@RequestBody UserVo user) {
        try {
            userService.createUser(user);
            return new BaseResponse<>("create success");
        } catch (DataIntegrityViolationException e) {
            return new BaseResponse<>(new DataIntegrityViolationException("data integrity exception"));
        } catch (Exception e) {
            log.error("Error in UserController.createUser()", e);
            return new BaseResponse<>(e);
        }
    }

    @GetMapping("/user")
    public BaseResponse<List<UserVo>> getAllUser() {
        return new BaseResponse<>(userService.getAllUser());
    }

    @GetMapping("/user/{id}")
    public BaseResponse<UserVo> getUserById(@PathVariable Long id) {
        try {
            UserVo user = userService.getUserById(id);
            return new BaseResponse<>(user);
        } catch (DataNotFoundException e) {
            return new BaseResponse<>(e);
        } catch (Exception e) {
            log.error("Error in UserController.getUserById()", e);
            return new BaseResponse<>(e);
        }
    }

    @PutMapping("/user/{id}")
    public BaseResponse<String> updateUser(@PathVariable Long id, @RequestBody UserVo user) {
        try {
            user.setId(id);
            userService.updateUser(user);
            return new BaseResponse<>("update success");
        } catch (DataIntegrityViolationException e) {
            return new BaseResponse<>(new DataIntegrityViolationException("data integrity exception"));
        } catch (Exception e) {
            log.error("Error in UserController.updateUser()", e);
            return new BaseResponse<>(e);
        }
    }

    @DeleteMapping("/user/{id}")
    public BaseResponse<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new BaseResponse<>("delete success");
        } catch (Exception e) {
            log.error("Error in UserController.deleteUser()", e);
            return new BaseResponse<>(e);
        }
    }
}