package com.programacion.web.service;

import com.programacion.web.model.Post;
import com.programacion.web.repository.PostRepository;

import java.util.List;

public class PostService {

    private final PostRepository repository;

    public PostService(
            PostRepository repository
    ) {

        this.repository = repository;

    }

    public List<Post> findAll() {

        return repository.findAll();

    }

    public Post findById(
            Integer id
    ) {

        return repository.findById(id);

    }

    public Post create(
            Post post
    ) {

        return repository.create(post);

    }

    public Post update(
            Integer id,
            Post post
    ) {

        return repository.update(
                id,
                post
        );

    }

    public boolean delete(
            Integer id
    ) {

        return repository.delete(id);

    }

}
