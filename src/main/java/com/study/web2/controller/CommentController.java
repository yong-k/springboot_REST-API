package com.study.web2.controller;

import com.study.web2.consts.ResultCode;
import com.study.web2.dto.CommonRespDto;
import com.study.web2.dto.comment.CreateCommentReqDto;
import com.study.web2.dto.comment.GetAllCommentRespDto;
import com.study.web2.dto.comment.GetCommentRespDto;
import com.study.web2.dto.comment.UpdateCommentReqDto;
import com.study.web2.exception.DataNotFoundException;
import com.study.web2.service.CommentService;
import com.study.web2.utils.Pagination;
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
            commonRespDto.setCode(ResultCode.DATA_INTEGRITY_VIOLATION.value());
            commonRespDto.setMessage("INSERT fail");
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in CommentController.createComment()", e);
        }
        return commonRespDto;
    }

    @GetMapping("/comment")
    public GetAllCommentRespDto getAllComment(@RequestParam(required = false) Long postId,
                                              @RequestParam(required = false) String name,
                                              @RequestParam(required = false) String email,
                                              @RequestParam(required = false) String body,
                                              @RequestParam(required = false, defaultValue = "1") int pageNum,
                                              @RequestParam(required = false, defaultValue = "10") int numOfRows) {
        GetAllCommentRespDto getAllCommentRespDto = new GetAllCommentRespDto();
        try {
            int totalDataCount = commentService.countComment(postId, name, email, body);
            getAllCommentRespDto.setPage(new Pagination(totalDataCount, numOfRows, pageNum));
            getAllCommentRespDto.setCommentList(commentService.getAllComment(pageNum, numOfRows, postId, name, email, body));
        } catch (Exception e) {
            getAllCommentRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            getAllCommentRespDto.setMessage("Unexpected Error");
            log.error("Error in CommentController.getAllComment()", e);
        }
        return getAllCommentRespDto;
    }

    @GetMapping("/comment/{id}")
    public GetCommentRespDto getCommentById(@PathVariable long id) {
        GetCommentRespDto getCommentRespDto = new GetCommentRespDto();
        try {
            getCommentRespDto.setComment(commentService.getCommentById(id));
        } catch (DataNotFoundException e) {
            getCommentRespDto.setCode(ResultCode.DATA_NOT_FOUND.value());
            getCommentRespDto.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            getCommentRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            getCommentRespDto.setMessage("Unexpected Error");
            log.error("Error in CommentController.getCommentById()", e);
        }
        return getCommentRespDto;
    }

    @PutMapping("/comment/{id}")
    public CommonRespDto updateComment(@PathVariable long id, @RequestBody UpdateCommentReqDto updateCommentReqDto) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            CommentVo comment = new CommentVo(updateCommentReqDto);
            comment.setId(id);
            commentService.updateComment(comment);
        } catch (DataNotFoundException e) {
            commonRespDto.setCode(ResultCode.DATA_NOT_FOUND.value());
            commonRespDto.setMessage(e.getLocalizedMessage());
        } catch (DataIntegrityViolationException e) {
            commonRespDto.setCode(ResultCode.DATA_INTEGRITY_VIOLATION.value());
            commonRespDto.setMessage("UPDATE fail");
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in CommentController.updateComment()", e);
        }
        return commonRespDto;
    }

    @DeleteMapping("/comment/{id}")
    public CommonRespDto deleteComment(@PathVariable long id) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            commentService.deleteComment(id);
        } catch (DataNotFoundException e) {
            commonRespDto.setCode(ResultCode.DATA_NOT_FOUND.value());
            commonRespDto.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in CommentController.deleteComment()", e);
        }
        return commonRespDto;
    }
}