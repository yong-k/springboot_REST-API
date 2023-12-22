package com.study.web2.dto.album;

import lombok.Data;

@Data
public class CreateAlbumReqDto {
    private Long userId;
    private String title;
}