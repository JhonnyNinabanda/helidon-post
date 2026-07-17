package com.programacion.web.handler;

import com.programacion.web.model.Todo;
import com.programacion.web.response.ErrorResponse;
import com.programacion.web.response.MessageResponse;
import com.programacion.web.service.TodoService;
import com.programacion.web.util.CorsUtil;

import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

public class TodoHandler {

    private final TodoService service;

    public TodoHandler(
            TodoService service
    ) {

        this.service = service;

    }

    public void findAll(
            ServerRequest request,
            ServerResponse response
    ) {
        CorsUtil.apply(response);

        response.send(
                service.findAll()
        );

    }

    public void findById(
            ServerRequest request,
            ServerResponse response
    ) {
        CorsUtil.apply(response);

        Integer id =
                Integer.parseInt(
                        request.path()
                                .pathParameters()
                                .get("id")
                );

        Todo todo =
                service.findById(id);

        if (todo == null) {

            response.status(404);

            response.send(
                    new ErrorResponse(
                            "Todo no encontrado"
                    )
            );

            return;

        }

        response.send(todo);

    }

    public void create(
            ServerRequest request,
            ServerResponse response
    ) {

        CorsUtil.apply(response);

        try {

            Todo todo =
                    request.content()
                            .as(Todo.class);

            Todo created =
                    service.create(todo);

            response.status(201);

            response.send(created);

        } catch (
                IllegalArgumentException e
        ) {

            response.status(400);

            response.send(

                    new ErrorResponse(
                            e.getMessage()
                    )

            );

        }

    }

    public void update(
            ServerRequest request,
            ServerResponse response
    ) {

        CorsUtil.apply(response);

        try {

            Integer id =
                    Integer.parseInt(
                            request.path()
                                    .pathParameters()
                                    .get("id")
                    );

            Todo todo =
                    request.content()
                            .as(Todo.class);

            Todo updated =
                    service.update(
                            id,
                            todo
                    );

            response.send(updated);

        } catch (
                IllegalArgumentException e
        ) {

            response.status(400);

            response.send(

                    new ErrorResponse(
                            e.getMessage()
                    )

            );

        }

    }

    public void delete(
            ServerRequest request,
            ServerResponse response
    ) {
        CorsUtil.apply(response);

        Integer id =
                Integer.parseInt(
                        request.path()
                                .pathParameters()
                                .get("id")
                );

        service.delete(id);

        response.send(
                new MessageResponse(
                        "Todo eliminado"
                )
        );

    }

}