package com.programacion.web.service;

import com.programacion.web.model.Photo;
import com.programacion.web.repository.PhotoRepository;

import java.util.List;

public class PhotoService {

    private final PhotoRepository repository;

    private final AlbumService albumService;

    public PhotoService(
            PhotoRepository repository,
            AlbumService albumService
    ) {

        this.repository = repository;
        this.albumService = albumService;

    }

    public List<Photo> findAll() {

        return repository.findAll();

    }

    public Photo findById(
            Integer id
    ) {

        return repository.findById(id);

    }

    public Photo create(
            Photo photo
    ) {

        if (!albumService.existsById(
                photo.albumId()
        )) {

            throw new IllegalArgumentException(
                    "albumId no existe"
            );

        }

        return repository.create(photo);

    }

    public Photo update(
            Integer id,
            Photo photo
    ) {

        if (!albumService.existsById(
                photo.albumId()
        )) {

            throw new IllegalArgumentException(
                    "albumId no existe"
            );

        }

        return repository.update(
                id,
                photo
        );

    }

    public boolean delete(
            Integer id
    ) {

        return repository.delete(id);

    }

}
