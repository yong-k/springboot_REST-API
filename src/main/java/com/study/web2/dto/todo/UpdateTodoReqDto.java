package com.study.web2.dto.todo;

import lombok.Data;

@Data
public class UpdateTodoReqDto {
    private String title;
    private Integer completed;
}