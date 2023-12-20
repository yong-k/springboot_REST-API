package com.study.web2.controller;

import com.study.web2.exception.DataNotFoundException;
import com.study.web2.response.BaseResponse;
import com.study.web2.service.PostService;
import com.study.web2.vo.PostVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/post")
    public BaseResponse<String> createPost(@RequestBody PostVo post) {
        try {
            postService.createPost(post);
            return new BaseResponse<>("create success");
        } catch (DataIntegrityViolationException e) {
            return new BaseResponse<>(new DataIntegrityViolationException("data integrity exception"));
        } catch (Exception e) {
            log.error("Error in PostController.createPost()", e);
            return new BaseResponse<>(e);
        }
    }

    @GetMapping("/post")
    public BaseResponse<List<PostVo>> getAllPost(@RequestParam(required = false) Long userId) {
        return new BaseResponse<>(postService.getAllPost(userId));
    }

    @GetMapping("/post/{id}")
    public BaseResponse<PostVo> getPostById(@PathVariable Long id) {
        try {
            PostVo post = postService.getPostById(id);
            return new BaseResponse<>(post);
        } catch (DataNotFoundException e) {
            return new BaseResponse<>(e);
        } catch (Exception e) {
            log.error("Error in PostController.getPostById()", e);
            return new BaseResponse<>(e);
        }
    }

    @PutMapping("/post/{id}")
    public BaseResponse<String> updatePost(@PathVariable Long id, @RequestBody PostVo post) {
        try {
            post.setId(id);
            postService.updatePost(post);
            return new BaseResponse<>("update success");
        } catch (DataIntegrityViolationException e) {
            return new BaseResponse<>(new DataIntegrityViolationException("data integrity exception"));
        } catch (Exception e) {
            log.error("Error in PostController.updatePost()", e);
            return new BaseResponse<>(e);
        }
    }

    @DeleteMapping("/post/{id}")
    public BaseResponse<String> deletePost(@PathVariable Long id) {
        try {
            postService.deletePost(id);
            return new BaseResponse<>("delete success");
        } catch (Exception e) {
            log.error("Error in UserController.deletePost()", e);
            return new BaseResponse<>(e);
        }
    }
}