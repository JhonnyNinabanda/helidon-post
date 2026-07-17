package com.programacion.web.service;

import com.programacion.web.model.Album;
import com.programacion.web.repository.AlbumRepository;

import java.util.List;

public class AlbumService {

    private final AlbumRepository repository;

    private final UserService userService;

    public AlbumService(
            AlbumRepository repository,
            UserService userService
    ) {

        this.repository = repository;
        this.userService = userService;

    }

    public List<Album> findAll() {

        return repository.findAll();

    }

    public Album findById(
            Integer id
    ) {

        return repository.findById(id);

    }

    public Album create(
            Album album
    ) {

        if (!userService.existsById(
                album.userId()
        )) {

            throw new IllegalArgumentException(
                    "userId no existe"
            );

        }

        return repository.create(album);

    }

    public Album update(
            Integer id,
            Album album
    ) {

        if (!userService.existsById(
                album.userId()
        )) {

            throw new IllegalArgumentException(
                    "userId no existe"
            );

        }

        return repository.update(
                id,
                album
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