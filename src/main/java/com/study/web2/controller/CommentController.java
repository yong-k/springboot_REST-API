package com.study.web2.controller;

import com.study.web2.service.CommentService;
import com.study.web2.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/comment")
    public void registerComment(@RequestBody CommentVo comment) {
        commentService.registerComment(comment);
    }

    @GetMapping("/comment")
    public List<CommentVo> findAll(@RequestParam(required = false) Long postId) {
        return commentService.findAll(postId);
    }

    @GetMapping("/comment/{id}")
    public CommentVo findById(@PathVariable Long id) {
        return commentService.findById(id);
    }

    @PutMapping("/comment/{id}")
    public void updateComment(@PathVariable Long id, @RequestBody CommentVo comment) {
        comment.setId(id);
        commentService.updateComment(comment);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}