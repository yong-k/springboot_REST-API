package com.study.web2.dto.photo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.consts.ResultCode;
import com.study.web2.vo.PhotoVo;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAllPhotoRespDto {
    private ResultCode code;
    private String message;
    private List<PhotoVo> photoList;
}