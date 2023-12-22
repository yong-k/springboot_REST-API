package com.study.web2.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.dto.comment.CreateCommentReqDto;
import com.study.web2.dto.comment.UpdateCommentReqDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentVo {
    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;

    public CommentVo(CreateCommentReqDto createCommentReqDto) {
        this.postId = createCommentReqDto.getPostId();
        this.name = createCommentReqDto.getName();
        this.email = createCommentReqDto.getEmail();
        this.body = createCommentReqDto.getBody();
    }

    public CommentVo(UpdateCommentReqDto updateCommentReqDto) {
        this.name = updateCommentReqDto.getName();
        this.email = updateCommentReqDto.getEmail();
        this.body = updateCommentReqDto.getBody();
    }
}