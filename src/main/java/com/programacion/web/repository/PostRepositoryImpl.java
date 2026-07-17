package com.programacion.web.repository;

import com.programacion.web.config.DbClientProvider;
import com.programacion.web.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl
        implements PostRepository {

    @Override
    public List<Post> findAll() {

        List<Post> posts =
                new ArrayList<>();

        DbClientProvider.getDbClient()
                .execute()
                .query(
                        """
                        SELECT *
                        FROM posts
                        ORDER BY id
                        """
                )
                .forEach(row -> {

                    posts.add(

                            new Post(

                                    row.column("id")
                                            .as(Integer.class)
                                            .orElse(null),

                                    row.column("user_id")
                                            .as(Integer.class)
                                            .orElse(null),

                                    row.column("title")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("body")
                                            .as(String.class)
                                            .orElse(null)

                            )

                    );

                });

        return posts;

    }

    @Override
    public Post findById(
            Integer id
    ) {

        return DbClientProvider.getDbClient()
                .execute()
                .query(
                        """
                        SELECT *
                        FROM posts
                        WHERE id = ?
                        """,
                        id
                )
                .findFirst()
                .map(row ->

                        new Post(

                                row.column("id")
                                        .as(Integer.class)
                                        .orElse(null),

                                row.column("user_id")
                                        .as(Integer.class)
                                        .orElse(null),

                                row.column("title")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("body")
                                        .as(String.class)
                                        .orElse(null)

                        )

                )
                .orElse(null);

    }

    @Override
    public Post create(
            Post post
    ) {

        DbClientProvider.getDbClient()
                .execute()
                .insert(
                        """
                        INSERT INTO posts
                        (
                            id,
                            user_id,
                            title,
                            body
                        )
                        VALUES
                        (
                            ?, ?, ?, ?
                        )
                        """,
                        post.id(),
                        post.userId(),
                        post.title(),
                        post.body()
                );

        return post;

    }

    @Override
    public Post update(
            Integer id,
            Post post
    ) {

        DbClientProvider.getDbClient()
                .execute()
                .update(
                        """
                        UPDATE posts
                        SET
                            user_id=?,
                            title=?,
                            body=?
                        WHERE id=?
                        """,
                        post.userId(),
                        post.title(),
                        post.body(),
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
                        DELETE FROM posts
                        WHERE id = ?
                        """,
                        id
                );

        return true;

    }

}