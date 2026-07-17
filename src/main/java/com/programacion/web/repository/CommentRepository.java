package com.programacion.web.repository;

import com.programacion.web.model.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> findAll();

    Comment findById(Integer id);

    Comment create(Comment comment);

    Comment update(
            Integer id,
            Comment comment
    );

    boolean delete(Integer id);

}