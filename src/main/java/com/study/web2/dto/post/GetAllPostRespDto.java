package com.study.web2.dto.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.consts.ResultCode;
import com.study.web2.utils.Pagination;
import com.study.web2.vo.PostVo;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAllPostRespDto {
    private ResultCode code;
    private String message;
    private List<PostVo> postList;
    private Pagination page;
}