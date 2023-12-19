package com.study.web2.vo;

import lombok.Data;

@Data
public class CommentVo {
    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;
}
