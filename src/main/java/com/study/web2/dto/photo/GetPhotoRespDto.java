package com.study.web2.dto.photo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.consts.ResultCode;
import com.study.web2.vo.PhotoVo;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetPhotoRespDto {
    private ResultCode code;
    private String message;
    private PhotoVo photo;
}