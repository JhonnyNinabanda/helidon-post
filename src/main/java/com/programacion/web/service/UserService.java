package com.programacion.web.service;

import com.programacion.web.model.User;
import com.programacion.web.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository repository;

    public UserService(
            UserRepository repository
    ) {

        this.repository = repository;

    }

    public List<User> findAll() {

        return repository.findAll();

    }

    public User findById(
            Integer id
    ) {

        return repository.findById(id);

    }

    public User create(
            User user
    ) {

        return repository.create(user);

    }

    public User update(
            Integer id,
            User user
    ) {

        return repository.update(
                id,
                user
        );

    }

    public boolean delete(
            Integer id
    ) {

        return repository.delete(id);

    }

    public boolean existsById(
            Integer id
    ) {

        return repository.existsById(id);

    }

}