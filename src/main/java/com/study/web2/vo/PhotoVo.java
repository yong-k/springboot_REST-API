package com.study.web2.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.dto.photo.CreatePhotoReqDto;
import com.study.web2.dto.photo.UpdatePhotoReqDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoVo {
    private long id;
    private long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;

    public PhotoVo(CreatePhotoReqDto createPhotoReqDto) {
        this.albumId = createPhotoReqDto.getAlbumId();
        this.title = createPhotoReqDto.getTitle();
        this.url = createPhotoReqDto.getUrl();
        this.thumbnailUrl = createPhotoReqDto.getThumbnailUrl();
    }

    public PhotoVo(UpdatePhotoReqDto updatePhotoReqDto) {
        this.title = updatePhotoReqDto.getTitle();
        this.url = updatePhotoReqDto.getUrl();
        this.thumbnailUrl = updatePhotoReqDto.getThumbnailUrl();
    }
}