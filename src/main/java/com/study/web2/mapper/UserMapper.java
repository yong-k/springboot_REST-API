package com.study.web2.mapper;

import com.study.web2.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int registerUser(UserVo user);

    List<UserVo> findAll();

    UserVo findById(Long id);

    int updateUser(UserVo user);

    int deleteUser(Long id);
}