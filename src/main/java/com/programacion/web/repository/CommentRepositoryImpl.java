package com.programacion.web.repository;

import com.programacion.web.config.DbClientProvider;
import com.programacion.web.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl
        implements CommentRepository {

    @Override
    public List<Comment> findAll() {

        List<Comment> comments =
                new ArrayList<>();

        DbClientProvider.getDbClient()
                .execute()
                .query(
                        """
                        SELECT *
                        FROM comments
                        ORDER BY id
                        """
                )
                .forEach(row -> {

                    comments.add(

                            new Comment(

                                    row.column("id")
                                            .as(Integer.class)
                                            .orElse(null),

                                    row.column("post_id")
                                            .as(Integer.class)
                                            .orElse(null),

                                    row.column("name")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("email")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("body")
                                            .as(String.class)
                                            .orElse(null)

                            )

                    );

                });

        return comments;

    }

    @Override
    public Comment findById(
            Integer id
    ) {

        return DbClientProvider.getDbClient()
                .execute()
                .query(
                        """
                        SELECT *
                        FROM comments
                        WHERE id = ?
                        """,
                        id
                )
                .findFirst()
                .map(row ->

                        new Comment(

                                row.column("id")
                                        .as(Integer.class)
                                        .orElse(null),

                                row.column("post_id")
                                        .as(Integer.class)
                                        .orElse(null),

                                row.column("name")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("email")
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
    public Comment create(
            Comment comment
    ) {

        DbClientProvider.getDbClient()
                .execute()
                .insert(
                        """
                        INSERT INTO comments
                        (
                            id,
                            post_id,
                            name,
                            email,
                            body
                        )
                        VALUES
                        (
                            ?, ?, ?, ?, ?
                        )
                        """,
                        comment.id(),
                        comment.postId(),
                        comment.name(),
                        comment.email(),
                        comment.body()
                );

        return comment;

    }

    @Override
    public Comment update(
            Integer id,
            Comment comment
    ) {

        DbClientProvider.getDbClient()
                .execute()
                .update(
                        """
                        UPDATE comments
                        SET
                            post_id=?,
                            name=?,
                            email=?,
                            body=?
                        WHERE id=?
                        """,
                        comment.postId(),
                        comment.name(),
                        comment.email(),
                        comment.body(),
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
                        DELETE FROM comments
                        WHERE id = ?
                        """,
                        id
                );

        return true;

    }

}