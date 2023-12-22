package com.study.web2.controller;

import com.study.web2.consts.ResultCode;
import com.study.web2.dto.CommonRespDto;
import com.study.web2.dto.comment.CreateCommentReqDto;
import com.study.web2.dto.comment.GetAllCommentRespDto;
import com.study.web2.dto.comment.GetCommentRespDto;
import com.study.web2.dto.comment.UpdateCommentReqDto;
import com.study.web2.exception.DataNotFoundException;
import com.study.web2.service.CommentService;
import com.study.web2.vo.CommentVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public CommonRespDto createComment(@RequestBody CreateCommentReqDto createCommentReqDto) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            commentService.createComment(new CommentVo(createCommentReqDto));
        } catch (DataIntegrityViolationException e) {
            commonRespDto.setCode(ResultCode.DATA_INTEGRITY_VIOLATION);
            commonRespDto.setMessage("INSERT fail");
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in CommentController.createComment()", e);
        }
        return commonRespDto;
    }

    @GetMapping("/comment")
    public GetAllCommentRespDto getAllComment(@RequestParam(required = false) Long postId) {
        GetAllCommentRespDto getAllCommentRespDto = new GetAllCommentRespDto();
        try {
            getAllCommentRespDto.setCommentList(commentService.getAllComment(postId));
        } catch (Exception e) {
            getAllCommentRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            getAllCommentRespDto.setMessage("Unexpected Error");
            log.error("Error in CommentController.getAllComment()", e);
        }
        return getAllCommentRespDto;
    }

    @GetMapping("/comment/{id}")
    public GetCommentRespDto getCommentById(@PathVariable Long id) {
        GetCommentRespDto getCommentRespDto = new GetCommentRespDto();
        try {
            getCommentRespDto.setComment(commentService.getCommentById(id));
        } catch (DataNotFoundException e) {
            getCommentRespDto.setCode(ResultCode.DATA_NOT_FOUND);
            getCommentRespDto.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            getCommentRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            getCommentRespDto.setMessage("Unexpected Error");
            log.error("Error in CommentController.getCommentById()", e);
        }
        return getCommentRespDto;
    }

    @PutMapping("/comment/{id}")
    public CommonRespDto updateComment(@PathVariable Long id, @RequestBody UpdateCommentReqDto updateCommentReqDto) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            CommentVo comment = new CommentVo(updateCommentReqDto);
            comment.setId(id);
            commentService.updateComment(comment);
        } catch (DataNotFoundException e) {
            commonRespDto.setCode(ResultCode.DATA_NOT_FOUND);
            commonRespDto.setMessage(e.getLocalizedMessage());
        } catch (DataIntegrityViolationException e) {
            commonRespDto.setCode(ResultCode.DATA_INTEGRITY_VIOLATION);
            commonRespDto.setMessage("UPDATE fail");
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in CommentController.updateComment()", e);
        }
        return commonRespDto;
    }

    @DeleteMapping("/comment/{id}")
    public CommonRespDto deleteComment(@PathVariable Long id) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            commentService.deleteComment(id);
        } catch (DataNotFoundException e) {
            commonRespDto.setCode(ResultCode.DATA_NOT_FOUND);
            commonRespDto.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in CommentController.deleteComment()", e);
        }
        return commonRespDto;
    }
}