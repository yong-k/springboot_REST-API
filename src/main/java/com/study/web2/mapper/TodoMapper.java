package com.study.web2.mapper;

import com.study.web2.vo.TodoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {

    int registerTodo(TodoVo todo);

    List<TodoVo> findAll(Long userId);

    TodoVo findById(Long id);

    int updateTodo(TodoVo todo);

    int deleteTodo(Long id);
}