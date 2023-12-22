package com.study.web2.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.consts.ResultCode;
import com.study.web2.vo.CommentVo;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetCommentRespDto {
    private ResultCode code = ResultCode.SUCCESS;
    private String message;
    private CommentVo comment;
}