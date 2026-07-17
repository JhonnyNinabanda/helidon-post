package com.programacion.web.service;

import com.programacion.web.model.Todo;
import com.programacion.web.repository.TodoRepository;

import java.util.List;

public class TodoService {

    private final TodoRepository repository;

    public TodoService(
            TodoRepository repository
    ) {

        this.repository = repository;

    }

    public List<Todo> findAll() {

        return repository.findAll();

    }

    public Todo findById(
            Integer id
    ) {

        return repository.findById(id);

    }

    public Todo create(
            Todo todo
    ) {

        return repository.create(todo);

    }

    public Todo update(
            Integer id,
            Todo todo
    ) {

        return repository.update(
                id,
                todo
        );

    }

    public boolean delete(
            Integer id
    ) {

        return repository.delete(id);

    }

}
