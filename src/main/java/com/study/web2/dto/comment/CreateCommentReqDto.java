package com.study.web2.dto.comment;

import lombok.Data;

@Data
public class CreateCommentReqDto {
    private Long postId;
    private String name;
    private String email;
    private String body;
}