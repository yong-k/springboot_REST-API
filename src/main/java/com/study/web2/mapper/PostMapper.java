package com.study.web2.mapper;

import com.study.web2.vo.PostVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    int registerPost(PostVo post);

    List<PostVo> findAll(Long userId);

    PostVo findById(Long id);

    int updatePost(PostVo post);

    int deletePost(Long id);
}