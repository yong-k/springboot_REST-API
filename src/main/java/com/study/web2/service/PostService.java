package com.study.web2.service;

import com.study.web2.mapper.PostMapper;
import com.study.web2.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostMapper postMapper;

    public void registerPost(PostVo post) {
        postMapper.registerPost(post);
    }

    public List<PostVo> findAll(Long userId) {
        return postMapper.findAll(userId);
    }

    public PostVo findById(Long id) {
        return postMapper.findById(id);
    }

    public void updatePost(PostVo post) {
        postMapper.updatePost(post);
    }

    public void deletePost(Long id) {
        postMapper.deletePost(id);
    }
}