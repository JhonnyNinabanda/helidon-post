package com.programacion.web.repository;

import com.programacion.web.model.User;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

    User findById(Integer id);

    User create(User user);

    User update(
            Integer id,
            User user
    );

    boolean delete(Integer id);

}