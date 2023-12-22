package com.study.web2.dto.todo;

import lombok.Data;

@Data
public class CreateTodoReqDto {
    private Long userId;
    private String title;
    private Integer completed;
}