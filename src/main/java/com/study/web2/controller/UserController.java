package com.study.web2.controller;

import com.study.web2.consts.ResultCode;
import com.study.web2.dto.*;
import com.study.web2.dto.user.CreateUserReqDto;
import com.study.web2.dto.user.GetAllUserRespDto;
import com.study.web2.dto.user.GetUserRespDto;
import com.study.web2.dto.user.UpdateUserReqDto;
import com.study.web2.exception.DataNotFoundException;
import com.study.web2.service.UserService;
import com.study.web2.utils.Pagination;
import com.study.web2.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public CommonRespDto createUser(@RequestBody CreateUserReqDto createUserReqDto) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            userService.createUser(new UserVo(createUserReqDto));
        } catch (DataIntegrityViolationException e) {
            commonRespDto.setCode(ResultCode.DATA_INTEGRITY_VIOLATION);
            commonRespDto.setMessage("INSERT fail");
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in UserController.createUser()", e);
        }
        return commonRespDto;
    }

    @GetMapping("/user")
    public GetAllUserRespDto getAllUser(@RequestParam(required = false) String username,
                                        @RequestParam(required = false) String email,
                                        @RequestParam(required = false, defaultValue = "1") int pageNum,
                                        @RequestParam(required = false, defaultValue = "10") int numOfRows) {
        GetAllUserRespDto getAllUserRespDto = new GetAllUserRespDto();
        try {
            int totalDataCount = userService.countUser(username, email);
            getAllUserRespDto.setPage(new Pagination(totalDataCount, numOfRows, pageNum));
            getAllUserRespDto.setUserList(userService.getAllUser(pageNum, numOfRows, username, email));
        } catch (Exception e) {
            getAllUserRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            getAllUserRespDto.setMessage("Unexpected Error");
            log.error("Error in UserController.getAllUser()", e);
        }
        return getAllUserRespDto;
    }

    @GetMapping("/user/{id}")
    public GetUserRespDto getUserById(@PathVariable Long id) {
        GetUserRespDto getUserRespDto = new GetUserRespDto();
        try {
            getUserRespDto.setUser(userService.getUserById(id));
        } catch (DataNotFoundException e) {
            getUserRespDto.setCode(ResultCode.DATA_NOT_FOUND);
            getUserRespDto.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            getUserRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            getUserRespDto.setMessage("Unexpected Error");
            log.error("Error in UserController.getUserById()", e);
        }
        return getUserRespDto;
    }

    @PutMapping("/user/{id}")
    public CommonRespDto updateUser(@PathVariable Long id, @RequestBody UpdateUserReqDto updateUserReqDto) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            UserVo user = new UserVo(updateUserReqDto);
            user.setId(id);
            userService.updateUser(user);
        } catch (DataNotFoundException e) {
            commonRespDto.setCode(ResultCode.DATA_NOT_FOUND);
            commonRespDto.setMessage(e.getLocalizedMessage());
        } catch (DataIntegrityViolationException e) {
            commonRespDto.setCode(ResultCode.DATA_INTEGRITY_VIOLATION);
            commonRespDto.setMessage("UPDATE fail");
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in UserController.updateUser()", e);
        }
        return commonRespDto;
    }

    @DeleteMapping("/user/{id}")
    public CommonRespDto deleteUser(@PathVariable Long id) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            userService.deleteUser(id);
        } catch (DataNotFoundException e) {
            commonRespDto.setCode(ResultCode.DATA_NOT_FOUND);
            commonRespDto.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in UserController.deleteUser()", e);
        }
        return commonRespDto;
    }
}