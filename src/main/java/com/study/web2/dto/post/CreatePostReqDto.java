package com.study.web2.dto.post;

import lombok.Data;

@Data
public class CreatePostReqDto {
    private Long userId;
    private String title;
    private String body;
}