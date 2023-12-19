package com.study.web2.controller;

import com.study.web2.service.PostService;
import com.study.web2.vo.PostVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/post")
    public void registerPost(@RequestBody PostVo post) {
        postService.registerPost(post);
    }

    @GetMapping("/post")
    public List<PostVo> findAll(@RequestParam(required = false) Long userId) {
        return postService.findAll(userId);
    }

    @GetMapping("/post/{id}")
    public PostVo findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PutMapping("/post/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody PostVo post) {
        post.setId(id);
        postService.updatePost(post);
    }

    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}