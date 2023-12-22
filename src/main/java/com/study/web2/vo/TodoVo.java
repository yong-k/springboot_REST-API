package com.study.web2.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.web2.dto.todo.CreateTodoReqDto;
import com.study.web2.dto.todo.UpdateTodoReqDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoVo {
    private Long id;
    private Long userId;
    private String title;
    private Integer completed;

    public TodoVo(CreateTodoReqDto createTodoReqDto) {
        this.userId = createTodoReqDto.getUserId();
        this.title = createTodoReqDto.getTitle();
        this.completed = createTodoReqDto.getCompleted();
    }

    public TodoVo(UpdateTodoReqDto updateTodoReqDto) {
        this.title = updateTodoReqDto.getTitle();
        this.completed = updateTodoReqDto.getCompleted();
    }
}