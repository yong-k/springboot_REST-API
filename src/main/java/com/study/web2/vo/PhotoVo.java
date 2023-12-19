package com.study.web2.vo;

import lombok.Data;

@Data
public class PhotoVo {
    private Long id;
    private Long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
}
