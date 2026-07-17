package com.programacion.web.service;

import com.programacion.web.model.Post;
import com.programacion.web.repository.PostRepository;

import java.util.List;

public class PostService {

    private final PostRepository repository;

    private final UserService userService;

    public PostService(
            PostRepository repository,
            UserService userService
    ) {

        this.repository = repository;
        this.userService = userService;

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

        if (!userService.existsById(
                post.userId()
        )) {

            throw new IllegalArgumentException(
                    "userId no existe"
            );

        }

        return repository.create(post);

    }

    public Post update(
            Integer id,
            Post post
    ) {

        if (!userService.existsById(
                post.userId()
        )) {

            throw new IllegalArgumentException(
                    "userId no existe"
            );

        }

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

    public boolean existsById(
            Integer id
    ) {

        return repository.existsById(id);

    }

}
