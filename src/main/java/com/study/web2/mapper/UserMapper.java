package com.study.web2.mapper;

import com.study.web2.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int createUser(UserVo user);

    List<UserVo> getAllUser(int pageNum, int numOfRows, String username, String email);

    int countUser(String username, String email);

    UserVo getUserById(long id);

    int updateUser(UserVo user);

    int deleteUser(long id);
}