package com.study.web2.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.consts.ResultCode;
import com.study.web2.vo.PostVo;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetPostRespDto {
    private int code = ResultCode.SUCCESS.value();
    private String message;
    private PostVo post;
}