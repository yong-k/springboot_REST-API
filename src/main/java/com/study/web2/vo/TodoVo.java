package com.study.web2.vo;

import lombok.Data;

@Data
public class TodoVo {
    private Long id;
    private Long userId;
    private String title;
    private Integer completed;
}
