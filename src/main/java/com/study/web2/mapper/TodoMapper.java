package com.study.web2.mapper;

import com.study.web2.vo.TodoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {

    int createTodo(TodoVo todo);

    List<TodoVo> getAllTodo(int pageNum, int numOfRows, Long userId, String title, Integer completed);

    int countTodo(Long userId, String title, Integer completed);

    TodoVo getTodoById(Long id);

    int updateTodo(TodoVo todo);

    int deleteTodo(Long id);
}