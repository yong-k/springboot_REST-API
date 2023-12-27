package com.study.web2.controller;

import com.study.web2.consts.ResultCode;
import com.study.web2.dto.CommonRespDto;
import com.study.web2.dto.todo.CreateTodoReqDto;
import com.study.web2.dto.todo.GetAllTodoRespDto;
import com.study.web2.dto.todo.GetTodoRespDto;
import com.study.web2.dto.todo.UpdateTodoReqDto;
import com.study.web2.exception.DataNotFoundException;
import com.study.web2.service.TodoService;
import com.study.web2.utils.Pagination;
import com.study.web2.vo.TodoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/todo")
    public CommonRespDto createTodo(@RequestBody CreateTodoReqDto createTodoReqDto) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            todoService.createTodo(new TodoVo(createTodoReqDto));
        } catch (DataIntegrityViolationException e) {
            commonRespDto.setCode(ResultCode.DATA_INTEGRITY_VIOLATION.value());
            commonRespDto.setMessage("INSERT fail");
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in TodoController.createTodo()", e);
        }
        return commonRespDto;
    }

    @GetMapping("/todo")
    public GetAllTodoRespDto getAllTodo(@RequestParam(required = false) Long userId,
                                        @RequestParam(required = false) String title,
                                        @RequestParam(required = false) Integer completed,
                                        @RequestParam(required = false, defaultValue = "1") int pageNum,
                                        @RequestParam(required = false, defaultValue = "10") int numOfRows) {
        GetAllTodoRespDto getAllTodoRespDto = new GetAllTodoRespDto();
        try {
            int totalDataCount = todoService.countTodo(userId, title, completed);
            getAllTodoRespDto.setPage(new Pagination(totalDataCount, numOfRows, pageNum));
            getAllTodoRespDto.setTodoList(todoService.getAllTodo(pageNum, numOfRows, userId, title, completed));
        } catch (Exception e) {
            getAllTodoRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            getAllTodoRespDto.setMessage("Unexpected Error");
            log.error("Error in TodoController.getAllTodo()", e);
        }
        return getAllTodoRespDto;
    }

    @GetMapping("/todo/{id}")
    public GetTodoRespDto getTodoById(@PathVariable long id) {
        GetTodoRespDto getTodoRespDto = new GetTodoRespDto();
        try {
            getTodoRespDto.setTodo(todoService.getTodoById(id));
        } catch (DataNotFoundException e) {
            getTodoRespDto.setCode(ResultCode.DATA_NOT_FOUND.value());
            getTodoRespDto.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            getTodoRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            getTodoRespDto.setMessage("Unexpected Error");
            log.error("Error in TodoController.getTodoById()", e);
        }
        return getTodoRespDto;
    }

    @PutMapping("/todo/{id}")
    public CommonRespDto updateTodo(@PathVariable long id, @RequestBody UpdateTodoReqDto updateTodoReqDto) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            TodoVo todo = new TodoVo(updateTodoReqDto);
            todo.setId(id);
            todoService.updateTodo(todo);
        } catch (DataNotFoundException e) {
            commonRespDto.setCode(ResultCode.DATA_NOT_FOUND.value());
            commonRespDto.setMessage(e.getLocalizedMessage());
        } catch (DataIntegrityViolationException e) {
            commonRespDto.setCode(ResultCode.DATA_INTEGRITY_VIOLATION.value());
            commonRespDto.setMessage("UPDATE fail");
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in TodoController.updateTodo()", e);
        }
        return commonRespDto;
    }

    @DeleteMapping("/todo/{id}")
    public CommonRespDto deleteTodo(@PathVariable long id) {
        CommonRespDto commonRespDto = new CommonRespDto();
        try {
            todoService.deleteTodo(id);
        } catch (DataNotFoundException e) {
            commonRespDto.setCode(ResultCode.DATA_NOT_FOUND.value());
            commonRespDto.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            commonRespDto.setCode(ResultCode.UNKNOWN_ERROR.value());
            commonRespDto.setMessage("Unexpected Error");
            log.error("Error in TodoController.deleteTodo()", e);
        }
        return commonRespDto;
    }
}