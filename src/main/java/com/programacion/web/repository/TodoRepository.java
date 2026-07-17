package com.programacion.web.repository;

import com.programacion.web.model.Todo;

import java.util.List;

public interface TodoRepository {

    List<Todo> findAll();

    Todo findById(Integer id);

    Todo create(Todo todo);

    Todo update(
            Integer id,
            Todo todo
    );

    boolean delete(Integer id);

}