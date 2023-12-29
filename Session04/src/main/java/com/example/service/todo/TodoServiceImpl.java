package com.example.service.todo;

import com.example.model.entity.Todo;
import com.example.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo saveOrUpdate(Todo todo) {
        return todoRepository.save(todo);
    }


    @Override
    public Todo findById(int id) {
        return todoRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(int id) {
        todoRepository.deleteById(id);
    }
}
