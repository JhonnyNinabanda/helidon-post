package com.programacion.web.repository;

import com.programacion.web.model.Album;

import java.util.List;

public interface AlbumRepository {

    List<Album> findAll();

    Album findById(Integer id);

    boolean existsById(Integer id);

    Album create(Album album);

    Album update(
            Integer id,
            Album album
    );

    boolean delete(Integer id);

}