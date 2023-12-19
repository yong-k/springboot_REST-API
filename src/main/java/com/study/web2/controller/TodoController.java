package com.study.web2.controller;

import com.study.web2.service.TodoService;
import com.study.web2.vo.TodoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    TodoService todoService;

    @PostMapping("/todo")
    public void registerTodo(@RequestBody TodoVo todo) {
        todoService.registerTodo(todo);
    }

    @GetMapping("/todo")
    public List<TodoVo> findAll(@RequestParam(required = false) Long userId) {
        return todoService.findAll(userId);
    }

    @GetMapping("/todo/{id}")
    public TodoVo findById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    @PutMapping("/todo/{id}")
    public void updateTodo(@PathVariable Long id, @RequestBody TodoVo todo) {
        todo.setId(id);
        todoService.updateTodo(todo);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
    }
}