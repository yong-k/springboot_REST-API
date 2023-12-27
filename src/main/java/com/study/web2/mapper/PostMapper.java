package com.study.web2.mapper;

import com.study.web2.vo.PostVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    int createPost(PostVo post);

    List<PostVo> getAllPost(int pageNum, int numOfRows, Long userId, String title, String body);

    int countPost(Long userId, String title, String body);

    PostVo getPostById(long id);

    int updatePost(PostVo post);

    int deletePost(long id);
}