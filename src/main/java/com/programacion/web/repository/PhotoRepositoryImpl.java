package com.programacion.web.repository;

import com.programacion.web.config.DbClientProvider;
import com.programacion.web.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoRepositoryImpl
        implements PhotoRepository {

    @Override
    public List<Photo> findAll() {

        List<Photo> photos =
                new ArrayList<>();

        DbClientProvider.getDbClient()
                .execute()
                .query(
                        """
                        SELECT *
                        FROM photos
                        ORDER BY id
                        """
                )
                .forEach(row -> {

                    photos.add(

                            new Photo(

                                    row.column("id")
                                            .as(Integer.class)
                                            .orElse(null),

                                    row.column("album_id")
                                            .as(Integer.class)
                                            .orElse(null),

                                    row.column("title")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("url")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("thumbnail_url")
                                            .as(String.class)
                                            .orElse(null)

                            )

                    );

                });

        return photos;

    }

    @Override
    public Photo findById(
            Integer id
    ) {

        return DbClientProvider.getDbClient()
                .execute()
                .query(
                        """
                        SELECT *
                        FROM photos
                        WHERE id = ?
                        """,
                        id
                )
                .findFirst()
                .map(row ->

                        new Photo(

                                row.column("id")
                                        .as(Integer.class)
                                        .orElse(null),

                                row.column("album_id")
                                        .as(Integer.class)
                                        .orElse(null),

                                row.column("title")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("url")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("thumbnail_url")
                                        .as(String.class)
                                        .orElse(null)

                        )

                )
                .orElse(null);

    }

    @Override
    public Photo create(
            Photo photo
    ) {

        DbClientProvider.getDbClient()
                .execute()
                .insert(
                        """
                        INSERT INTO photos
                        (
                            id,
                            album_id,
                            title,
                            url,
                            thumbnail_url
                        )
                        VALUES
                        (
                            ?, ?, ?, ?, ?
                        )
                        """,
                        photo.id(),
                        photo.albumId(),
                        photo.title(),
                        photo.url(),
                        photo.thumbnailUrl()
                );

        return photo;

    }

    @Override
    public Photo update(
            Integer id,
            Photo photo
    ) {

        DbClientProvider.getDbClient()
                .execute()
                .update(
                        """
                        UPDATE photos
                        SET
                            album_id=?,
                            title=?,
                            url=?,
                            thumbnail_url=?
                        WHERE id=?
                        """,
                        photo.albumId(),
                        photo.title(),
                        photo.url(),
                        photo.thumbnailUrl(),
                        id
                );

        return findById(id);

    }

    @Override
    public boolean delete(
            Integer id
    ) {

        DbClientProvider.getDbClient()
                .execute()
                .delete(
                        """
                        DELETE FROM photos
                        WHERE id = ?
                        """,
                        id
                );

        return true;

    }

}