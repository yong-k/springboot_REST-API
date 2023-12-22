package com.study.web2.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.consts.ResultCode;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonRespDto {
    private ResultCode code = ResultCode.SUCCESS;
    private String message;
}