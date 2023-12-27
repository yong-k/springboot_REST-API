package com.study.web2.dto.post;

import lombok.Data;

@Data
public class CreatePostReqDto {
    private long userId;
    private String title;
    private String body;
}