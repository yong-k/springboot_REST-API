package com.study.web2.dto.album;

import lombok.Data;

@Data
public class CreateAlbumReqDto {
    private long userId;
    private String title;
}