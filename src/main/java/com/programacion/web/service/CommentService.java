package com.programacion.web.service;

import com.programacion.web.model.Comment;
import com.programacion.web.repository.CommentRepository;

import java.util.List;

public class CommentService {

    private final CommentRepository repository;

    public CommentService(
            CommentRepository repository
    ) {

        this.repository = repository;

    }

    public List<Comment> findAll() {

        return repository.findAll();

    }

    public Comment findById(
            Integer id
    ) {

        return repository.findById(id);

    }

    public Comment create(
            Comment comment
    ) {

        return repository.create(comment);

    }

    public Comment update(
            Integer id,
            Comment comment
    ) {

        return repository.update(
                id,
                comment
        );

    }

    public boolean delete(
            Integer id
    ) {

        return repository.delete(id);

    }

}