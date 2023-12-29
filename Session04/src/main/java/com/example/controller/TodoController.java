package com.example.controller;

import com.example.model.entity.Todo;
import com.example.service.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodolist() {
        List<Todo> list = todoService.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Integer id) {
        Todo todo = todoService.findById(id);
        if (todo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo todoNew = todoService.saveOrUpdate(todo);
        return new ResponseEntity<>(todoNew, HttpStatus.OK);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody Todo todo) {
        Todo todoEdit = todoService.findById(id);
        if(todoEdit==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todo.setId(todoEdit.getId());
        return new ResponseEntity<>(todoService.saveOrUpdate(todo),HttpStatus.OK);
    }

    @PatchMapping("/todos/{id}")
    public ResponseEntity<?>complete(@PathVariable Integer id){
        Todo todoClose = todoService.findById(id);
        if(todoClose==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todoClose.setStatus(!todoClose.isStatus());
        return new ResponseEntity<>(todoService.saveOrUpdate(todoClose),HttpStatus.OK);
    }

}
