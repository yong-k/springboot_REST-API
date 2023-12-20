package com.study.web2.mapper;

import com.study.web2.vo.PostVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    int createPost(PostVo post);

    List<PostVo> getAllPost(Long userId);

    PostVo getPostById(Long id);

    int updatePost(PostVo post);

    int deletePost(Long id);
}