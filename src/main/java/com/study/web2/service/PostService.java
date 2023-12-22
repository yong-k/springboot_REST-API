package com.study.web2.service;

import com.study.web2.exception.DataNotFoundException;
import com.study.web2.mapper.PostMapper;
import com.study.web2.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    public void createPost(PostVo post) {
        postMapper.createPost(post);
    }

    public List<PostVo> getAllPost(Long userId) {
        return postMapper.getAllPost(userId);
    }

    public PostVo getPostById(Long id) {
        PostVo post = postMapper.getPostById(id);
        if (post == null)
            throw new DataNotFoundException("Not exist post: id = " + id);
        return post;
    }

    public void updatePost(PostVo post) {
        int result = postMapper.updatePost(post);
        if (result < 1)
            throw new DataNotFoundException("[UPDATE fail] Not exist post: id = " + post.getId());
    }

    public void deletePost(Long id) {
        int result = postMapper.deletePost(id);
        if (result < 1)
            throw new DataNotFoundException("[DELETE fail] Not exist post: id = " + id);
    }
}