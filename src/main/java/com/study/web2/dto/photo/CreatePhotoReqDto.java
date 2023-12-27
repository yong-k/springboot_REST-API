package com.study.web2.dto.photo;

import lombok.Data;

@Data
public class CreatePhotoReqDto {
    private long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
}