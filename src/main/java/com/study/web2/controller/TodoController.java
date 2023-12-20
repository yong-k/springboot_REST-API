package com.study.web2.controller;

import com.study.web2.exception.DataNotFoundException;
import com.study.web2.response.BaseResponse;
import com.study.web2.service.TodoService;
import com.study.web2.vo.TodoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class TodoController {

    @Autowired
    TodoService todoService;

    @PostMapping("/todo")
    public BaseResponse<String> createTodo(@RequestBody TodoVo todo) {
        try {
            todoService.createTodo(todo);
            return new BaseResponse<>("create success");
        } catch (DataIntegrityViolationException e) {
            return new BaseResponse<>(new DataIntegrityViolationException("data integrity exception"));
        } catch (Exception e) {
            log.error("Error in TodoController.createTodo()", e);
            return new BaseResponse<>(e);
        }
    }

    @GetMapping("/todo")
    public BaseResponse<List<TodoVo>> getAllTodo(@RequestParam(required = false) Long userId) {
        return new BaseResponse<>(todoService.getAllTodo(userId));
    }

    @GetMapping("/todo/{id}")
    public BaseResponse<TodoVo> getTodoById(@PathVariable Long id) {
        try {
            TodoVo todo = todoService.getTodoById(id);
            return new BaseResponse<>(todo);
        } catch (DataNotFoundException e) {
            return new BaseResponse<>(e);
        } catch (Exception e) {
            log.error("Error in TodoController.getTodoById()", e);
            return new BaseResponse<>(e);
        }
    }

    @PutMapping("/todo/{id}")
    public BaseResponse<String> updateTodo(@PathVariable Long id, @RequestBody TodoVo todo) {
        try {
            todo.setId(id);
            todoService.updateTodo(todo);
            return new BaseResponse<>("update success");
        } catch (DataIntegrityViolationException e) {
            return new BaseResponse<>(new DataIntegrityViolationException("data integrity exception"));
        } catch (Exception e) {
            log.error("Error in TodoController.updateTodo()", e);
            return new BaseResponse<>(e);
        }
    }

    @DeleteMapping("/todo/{id}")
    public BaseResponse<String> deleteTodo(@PathVariable Long id) {
        try {
            todoService.deleteTodo(id);
            return new BaseResponse<>("delete success");
        } catch (Exception e) {
            log.error("Error in TodoController.deleteTodo()", e);
            return new BaseResponse<>(e);
        }
    }
}