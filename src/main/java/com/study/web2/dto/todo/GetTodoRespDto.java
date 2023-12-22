package com.study.web2.dto.todo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.consts.ResultCode;
import com.study.web2.vo.TodoVo;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetTodoRespDto {
    private ResultCode code = ResultCode.SUCCESS;
    private String message;
    private TodoVo todo;
}