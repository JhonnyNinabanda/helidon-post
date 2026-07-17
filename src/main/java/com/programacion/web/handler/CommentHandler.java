package com.programacion.web.handler;

import com.programacion.web.model.Comment;
import com.programacion.web.response.ErrorResponse;
import com.programacion.web.response.MessageResponse;
import com.programacion.web.service.CommentService;

import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

public class CommentHandler {

    private final CommentService service;

    public CommentHandler(
            CommentService service
    ) {

        this.service = service;

    }

    public void findAll(
            ServerRequest request,
            ServerResponse response
    ) {

        response.send(
                service.findAll()
        );

    }

    public void findById(
            ServerRequest request,
            ServerResponse response
    ) {

        Integer id =
                Integer.parseInt(
                        request.path()
                                .pathParameters()
                                .get("id")
                );

        Comment comment =
                service.findById(id);

        if (comment == null) {

            response.status(404);

            response.send(
                    new ErrorResponse(
                            "Comment no encontrado"
                    )
            );

            return;

        }

        response.send(comment);

    }

    public void create(
            ServerRequest request,
            ServerResponse response
    ) {

        Comment comment =
                request.content()
                        .as(Comment.class);

        Comment created =
                service.create(comment);

        response.status(201);

        response.send(created);

    }

    public void update(
            ServerRequest request,
            ServerResponse response
    ) {

        Integer id =
                Integer.parseInt(
                        request.path()
                                .pathParameters()
                                .get("id")
                );

        Comment comment =
                request.content()
                        .as(Comment.class);

        response.send(
                service.update(
                        id,
                        comment
                )
        );

    }

    public void delete(
            ServerRequest request,
            ServerResponse response
    ) {

        Integer id =
                Integer.parseInt(
                        request.path()
                                .pathParameters()
                                .get("id")
                );

        service.delete(id);

        response.send(
                new MessageResponse(
                        "Comment eliminado"
                )
        );

    }

}