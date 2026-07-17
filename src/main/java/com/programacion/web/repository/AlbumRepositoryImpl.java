package com.programacion.web.repository;

import com.programacion.web.config.DbClientProvider;
import com.programacion.web.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumRepositoryImpl
        implements AlbumRepository {

    @Override
    public List<Album> findAll() {

        List<Album> albums =
                new ArrayList<>();

        DbClientProvider.getDbClient()
                .execute()
                .query(
                        """
                        SELECT *
                        FROM albums
                        ORDER BY id
                        """
                )
                .forEach(row -> {

                    albums.add(

                            new Album(

                                    row.column("id")
                                            .as(Integer.class)
                                            .orElse(null),

                                    row.column("user_id")
                                            .as(Integer.class)
                                            .orElse(null),

                                    row.column("title")
                                            .as(String.class)
                                            .orElse(null)

                            )

                    );

                });

        return albums;

    }

    @Override
    public Album findById(
            Integer id
    ) {

        return DbClientProvider.getDbClient()
                .execute()
                .query(
                        """
                        SELECT *
                        FROM albums
                        WHERE id = ?
                        """,
                        id
                )
                .findFirst()
                .map(row ->

                        new Album(

                                row.column("id")
                                        .as(Integer.class)
                                        .orElse(null),

                                row.column("user_id")
                                        .as(Integer.class)
                                        .orElse(null),

                                row.column("title")
                                        .as(String.class)
                                        .orElse(null)

                        )

                )
                .orElse(null);

    }

    @Override
    public Album create(
            Album album
    ) {

        DbClientProvider.getDbClient()
                .execute()
                .insert(
                        """
                        INSERT INTO albums
                        (
                            id,
                            user_id,
                            title
                        )
                        VALUES
                        (
                            ?, ?, ?
                        )
                        """,
                        album.id(),
                        album.userId(),
                        album.title()
                );

        return album;

    }

    @Override
    public Album update(
            Integer id,
            Album album
    ) {

        DbClientProvider.getDbClient()
                .execute()
                .update(
                        """
                        UPDATE albums
                        SET
                            user_id=?,
                            title=?
                        WHERE id=?
                        """,
                        album.userId(),
                        album.title(),
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
                        DELETE FROM albums
                        WHERE id = ?
                        """,
                        id
                );

        return true;

    }

    @Override
    public boolean existsById(
            Integer id
    ) {

        return DbClientProvider.getDbClient()
                .execute()
                .query(
                        """
                        SELECT id
                        FROM albums
                        WHERE id = ?
                        """,
                        id
                )
                .findFirst()
                .isPresent();

    }

}