package com.study.web2.controller;

import com.study.web2.consts.ResultCode;
import com.study.web2.dto.CommonRespDto;
import com.study.web2.dto.post.CreatePostReqDto;
import com.study.web2.dto.post.GetAllPostRespDto;
import com.study.web2.dto.post.GetPostRespDto;
import com.study.web2.dto.post.UpdatePostReqDto;
import com.study.web2.exception.DataNotFoundException;
import com.study.web2.service.PostService;
import com.study.web2.utils.Pagination;
import com.study.web2.vo.PostVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/post")
    public CommonRespDto createPost(@RequestBody CreatePostReqDto createPostReqDto) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            postService.createPost(new PostVo(createPostReqDto));
        } catch (DataIntegrityViolationException e) {
            commonRespDto.setCode(ResultCode.DATA_INTEGRITY_VIOLATION);
            commonRespDto.setMessage("INSERT fail");
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in PostController.createPost()", e);
        }
        return commonRespDto;
    }

    @GetMapping("/post")
    public GetAllPostRespDto getAllPost(@RequestParam(required = false) Long userId,
                                        @RequestParam(required = false) String title,
                                        @RequestParam(required = false) String body,
                                        @RequestParam(required = false, defaultValue = "1") int pageNum,
                                        @RequestParam(required = false, defaultValue = "10") int numOfRows) {
        GetAllPostRespDto getAllPostRespDto = new GetAllPostRespDto();
        try {
            int totalDataCount = postService.countPost(userId, title, body);
            getAllPostRespDto.setPage(new Pagination(totalDataCount, numOfRows, pageNum));
            getAllPostRespDto.setPostList(postService.getAllPost(pageNum, numOfRows, userId, title, body));
        } catch (Exception e) {
            getAllPostRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            getAllPostRespDto.setMessage("Unexpected Error");
            log.error("Error in PostController.getAllPost()", e);
        }
        return getAllPostRespDto;
    }

    @GetMapping("/post/{id}")
    public GetPostRespDto getPostById(@PathVariable Long id) {
        GetPostRespDto getPostRespDto = new GetPostRespDto();
        try {
            getPostRespDto.setPost(postService.getPostById(id));
        } catch (DataNotFoundException e) {
            getPostRespDto.setCode(ResultCode.DATA_NOT_FOUND);
            getPostRespDto.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            getPostRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            getPostRespDto.setMessage("Unexpected Error");
            log.error("Error in PostController.getPostById()", e);
        }
        return getPostRespDto;
    }

    @PutMapping("/post/{id}")
    public CommonRespDto updatePost(@PathVariable Long id, @RequestBody UpdatePostReqDto updatePostReqDto) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            PostVo post = new PostVo(updatePostReqDto);
            post.setId(id);
            postService.updatePost(post);
        } catch (DataNotFoundException e) {
            commonRespDto.setCode(ResultCode.DATA_NOT_FOUND);
            commonRespDto.setMessage(e.getLocalizedMessage());
        } catch (DataIntegrityViolationException e) {
            commonRespDto.setCode(ResultCode.DATA_INTEGRITY_VIOLATION);
            commonRespDto.setMessage("UPDATE fail");
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in PostController.updatePost()", e);
        }
        return commonRespDto;
    }

    @DeleteMapping("/post/{id}")
    public CommonRespDto deletePost(@PathVariable Long id) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            postService.deletePost(id);
        } catch (DataNotFoundException e) {
            commonRespDto.setCode(ResultCode.DATA_NOT_FOUND);
            commonRespDto.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR);
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in UserController.deletePost()", e);
        }
        return commonRespDto;
    }
}