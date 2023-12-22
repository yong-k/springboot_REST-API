package com.study.web2.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.consts.ResultCode;
import com.study.web2.vo.UserVo;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUserRespDto {
    private ResultCode code = ResultCode.SUCCESS;
    private String message;
    private UserVo user;
}