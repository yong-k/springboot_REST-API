package com.study.web2.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.consts.ResultCode;
import com.study.web2.utils.Pagination;
import com.study.web2.vo.CommentVo;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAllCommentRespDto {
    private int code = ResultCode.SUCCESS.value();
    private String message;
    private List<CommentVo> commentList;
    private Pagination page;
}