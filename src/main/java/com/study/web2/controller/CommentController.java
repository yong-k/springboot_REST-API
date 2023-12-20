package com.study.web2.controller;

import com.study.web2.exception.DataNotFoundException;
import com.study.web2.response.BaseResponse;
import com.study.web2.service.CommentService;
import com.study.web2.vo.CommentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/comment")
    public BaseResponse<String> createComment(@RequestBody CommentVo comment) {
        try {
            commentService.createComment(comment);
            return new BaseResponse<>("create success");
        } catch (DataIntegrityViolationException e) {
            return new BaseResponse<>(new DataIntegrityViolationException("data integrity exception"));
        } catch (Exception e) {
            log.error("Error in CommentController.createComment()", e);
            return new BaseResponse<>(e);
        }
    }

    @GetMapping("/comment")
    public BaseResponse<List<CommentVo>> getAllComment(@RequestParam(required = false) Long postId) {
        return new BaseResponse<>(commentService.getAllComment(postId));
    }

    @GetMapping("/comment/{id}")
    public BaseResponse<CommentVo> getCommentById(@PathVariable Long id) {
        try {
            CommentVo comment = commentService.getCommentById(id);
            return new BaseResponse<>(comment);
        } catch (DataNotFoundException e) {
            return new BaseResponse<>(e);
        } catch (Exception e) {
            log.error("Error in CommentController.getCommentById()", e);
            return new BaseResponse<>(e);
        }
    }

    @PutMapping("/comment/{id}")
    public BaseResponse<String> updateComment(@PathVariable Long id, @RequestBody CommentVo comment) {
        try {
            comment.setId(id);
            commentService.updateComment(comment);
            return new BaseResponse<>("update success");
        } catch (DataIntegrityViolationException e) {
            return new BaseResponse<>(new DataIntegrityViolationException("data integrity exception"));
        } catch (Exception e) {
            log.error("Error in CommentController.updateComment()", e);
            return new BaseResponse<>(e);
        }
    }

    @DeleteMapping("/comment/{id}")
    public BaseResponse<String> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            return new BaseResponse<>("delete success");
        } catch (Exception e) {
            log.error("Error in CommentController.deleteComment()", e);
            return new BaseResponse<>(e);
        }
    }
}