package com.programacion.web.repository;

import com.programacion.web.model.Photo;

import java.util.List;

public interface PhotoRepository {

    List<Photo> findAll();

    Photo findById(Integer id);

    Photo create(Photo photo);

    Photo update(
            Integer id,
            Photo photo
    );

    boolean delete(Integer id);

}