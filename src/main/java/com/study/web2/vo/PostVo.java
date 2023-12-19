package com.study.web2.vo;

import lombok.Data;

@Data
public class PostVo {
    private Long id;
    private Long userId;
    private String title;
    private String body;
}
