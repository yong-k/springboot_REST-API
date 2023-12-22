package com.study.web2.dto.album;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.consts.ResultCode;
import com.study.web2.vo.AlbumVo;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAlbumRespDto {
    private ResultCode code = ResultCode.SUCCESS;
    private String message;
    private AlbumVo album;
}