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
    private TodoMapper todoMapper;

    public void createTodo(TodoVo todo) {
        todoMapper.createTodo(todo);
    }

    public List<TodoVo> getAllTodo(int pageNum, int numOfRows, Long userId, String title, Integer completed) {
        pageNum = numOfRows * (pageNum - 1);
        return todoMapper.getAllTodo(pageNum, numOfRows, userId, title, completed);
    }

    public int countTodo(Long userId, String title, Integer completed) {
        return todoMapper.countTodo(userId, title, completed);
    }

    public TodoVo getTodoById(Long id) {
        TodoVo todo = todoMapper.getTodoById(id);
        if (todo == null)
            throw new DataNotFoundException("Not exist todo: id = " + id);
        return todo;
    }

    public void updateTodo(TodoVo todo) {
        int result = todoMapper.updateTodo(todo);
        if (result < 1)
            throw new DataNotFoundException("[UPDATE fail] Not exist todo: id = " + todo.getId());
    }

    public void deleteTodo(Long id) {
        int result = todoMapper.deleteTodo(id);
        if (result < 1)
            throw new DataNotFoundException("[DELETE fail] Not exist todo: id = " + id);
    }
}