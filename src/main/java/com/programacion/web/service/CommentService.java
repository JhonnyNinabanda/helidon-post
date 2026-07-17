package com.programacion.web.service;

import com.programacion.web.model.Comment;
import com.programacion.web.repository.CommentRepository;

import java.util.List;

public class CommentService {

    private final CommentRepository repository;

    private final PostService postService;

    public CommentService(
            CommentRepository repository,
            PostService postService
    ) {

        this.repository = repository;
        this.postService = postService;

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

        if (!postService.existsById(
                comment.postId()
        )) {

            throw new IllegalArgumentException(
                    "postId no existe"
            );

        }

        return repository.create(comment);

    }

    public Comment update(
            Integer id,
            Comment comment
    ) {

        if (!postService.existsById(
                comment.postId()
        )) {

            throw new IllegalArgumentException(
                    "postId no existe"
            );

        }

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