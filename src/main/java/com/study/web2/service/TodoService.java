package com.study.web2.service;

import com.study.web2.exception.DataNotFoundException;
import com.study.web2.mapper.TodoMapper;
import com.study.web2.vo.TodoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoMapper todoMapper;

    public void createTodo(TodoVo todo) {
        todoMapper.createTodo(todo);
    }

    public List<TodoVo> getAllTodo(Long userId) {
        return todoMapper.getAllTodo(userId);
    }

    public TodoVo getTodoById(Long id) {
        TodoVo todo = todoMapper.getTodoById(id);
        if (todo == null)
            throw new DataNotFoundException("Not exist todo: id = " + id);
        return todo;
    }

    public void updateTodo(TodoVo todo) {
        todoMapper.updateTodo(todo);
    }

    public void deleteTodo(Long id) {
        todoMapper.deleteTodo(id);
    }
}