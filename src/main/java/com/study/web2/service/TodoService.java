package com.study.web2.service;

import com.study.web2.mapper.TodoMapper;
import com.study.web2.vo.TodoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    TodoMapper todoMapper;

    public void registerTodo(TodoVo todo) {
        todoMapper.registerTodo(todo);
    }

    public List<TodoVo> findAll(Long userId) {
        return todoMapper.findAll(userId);
    }

    public TodoVo findById(Long id) {
        return todoMapper.findById(id);
    }

    public void updateTodo(TodoVo todo) {
        todoMapper.updateTodo(todo);
    }

    public void deleteTodo(Long id) {
        todoMapper.deleteTodo(id);
    }
}