package com.study.web2.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.dto.album.CreateAlbumReqDto;
import com.study.web2.dto.album.UpdateAlbumReqDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumVo {
    private long id;
    private long userId;
    private String title;

    public AlbumVo(CreateAlbumReqDto createAlbumReqDto) {
        this.userId = createAlbumReqDto.getUserId();
        this.title = createAlbumReqDto.getTitle();
    }

    public AlbumVo(UpdateAlbumReqDto updateAlbumReqDto) {
        this.title = updateAlbumReqDto.getTitle();
    }
}