package com.programacion.web.repository;

import com.programacion.web.model.Post;

import java.util.List;

public interface PostRepository {

    List<Post> findAll();

    boolean existsById(Integer id);

    Post findById(Integer id);

    Post create(Post post);

    Post update(
            Integer id,
            Post post
    );

    boolean delete(Integer id);

}