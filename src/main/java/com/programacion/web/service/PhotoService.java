package com.programacion.web.service;

import com.programacion.web.model.Photo;
import com.programacion.web.repository.PhotoRepository;

import java.util.List;

public class PhotoService {

    private final PhotoRepository repository;

    public PhotoService(
            PhotoRepository repository
    ) {

        this.repository = repository;

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

        return repository.create(photo);

    }

    public Photo update(
            Integer id,
            Photo photo
    ) {

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
