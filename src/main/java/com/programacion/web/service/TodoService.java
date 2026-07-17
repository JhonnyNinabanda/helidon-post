package com.programacion.web.service;

import com.programacion.web.model.Todo;
import com.programacion.web.repository.TodoRepository;

import java.util.List;

public class TodoService {

    private final TodoRepository repository;

    private final UserService userService;

    public TodoService(
            TodoRepository repository,
            UserService userService
    ) {

        this.repository = repository;
        this.userService = userService;

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

        if (!userService.existsById(
                todo.userId()
        )) {

            throw new IllegalArgumentException(
                    "userId no existe"
            );

        }

        return repository.create(todo);

    }

    public Todo update(
            Integer id,
            Todo todo
    ) {

        if (!userService.existsById(
                todo.userId()
        )) {

            throw new IllegalArgumentException(
                    "userId no existe"
            );

        }

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
