package com.programacion.web.repository;

import com.programacion.web.config.DbClientProvider;
import com.programacion.web.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoRepositoryImpl
        implements TodoRepository {

    @Override
    public List<Todo> findAll() {

        List<Todo> todos =
                new ArrayList<>();

        DbClientProvider.getDbClient()
                .execute()
                .query(
                        """
                        SELECT *
                        FROM todos
                        ORDER BY id
                        """
                )
                .forEach(row -> {

                    todos.add(

                            new Todo(

                                    row.column("id")
                                            .as(Integer.class)
                                            .orElse(null),

                                    row.column("user_id")
                                            .as(Integer.class)
                                            .orElse(null),

                                    row.column("title")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("completed")
                                            .as(Boolean.class)
                                            .orElse(null)

                            )

                    );

                });

        return todos;

    }

    @Override
    public Todo findById(
            Integer id
    ) {

        return DbClientProvider.getDbClient()
                .execute()
                .query(
                        """
                        SELECT *
                        FROM todos
                        WHERE id = ?
                        """,
                        id
                )
                .findFirst()
                .map(row ->

                        new Todo(

                                row.column("id")
                                        .as(Integer.class)
                                        .orElse(null),

                                row.column("user_id")
                                        .as(Integer.class)
                                        .orElse(null),

                                row.column("title")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("completed")
                                        .as(Boolean.class)
                                        .orElse(null)

                        )

                )
                .orElse(null);

    }

    @Override
    public Todo create(
            Todo todo
    ) {

        DbClientProvider.getDbClient()
                .execute()
                .insert(
                        """
                        INSERT INTO todos
                        (
                            id,
                            user_id,
                            title,
                            completed
                        )
                        VALUES
                        (
                            ?, ?, ?, ?
                        )
                        """,
                        todo.id(),
                        todo.userId(),
                        todo.title(),
                        todo.completed()
                );

        return todo;

    }

    @Override
    public Todo update(
            Integer id,
            Todo todo
    ) {

        DbClientProvider.getDbClient()
                .execute()
                .update(
                        """
                        UPDATE todos
                        SET
                            user_id=?,
                            title=?,
                            completed=?
                        WHERE id=?
                        """,
                        todo.userId(),
                        todo.title(),
                        todo.completed(),
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
                        DELETE FROM todos
                        WHERE id = ?
                        """,
                        id
                );

        return true;

    }

}