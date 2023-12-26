package com.study.web2.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.consts.ResultCode;
import com.study.web2.utils.Pagination;
import com.study.web2.vo.UserVo;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAllUserRespDto {
    private ResultCode code = ResultCode.SUCCESS;
    private String message;
    private List<UserVo> userList;
    private Pagination page;
}