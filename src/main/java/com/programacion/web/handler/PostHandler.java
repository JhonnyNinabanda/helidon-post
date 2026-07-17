package com.programacion.web.handler;

import com.programacion.web.model.Post;
import com.programacion.web.response.ErrorResponse;
import com.programacion.web.response.MessageResponse;
import com.programacion.web.service.PostService;

import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

public class PostHandler {

    private final PostService service;

    public PostHandler(
            PostService service
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

        Post post =
                service.findById(id);

        if (post == null) {

            response.status(404);

            response.send(

                    new ErrorResponse(
                            "Post no encontrado"
                    )

            );

            return;

        }

        response.send(post);

    }

    public void create(
            ServerRequest request,
            ServerResponse response
    ) {

        Post post =
                request.content()
                        .as(Post.class);

        Post created =
                service.create(post);

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

        Post post =
                request.content()
                        .as(Post.class);

        Post updated =
                service.update(
                        id,
                        post
                );

        if (updated == null) {

            response.status(404);

            response.send(

                    new ErrorResponse(
                            "Post no encontrado"
                    )

            );

            return;

        }

        response.send(updated);

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

        boolean deleted =
                service.delete(id);

        if (!deleted) {

            response.status(404);

            response.send(

                    new ErrorResponse(
                            "Post no encontrado"
                    )

            );

            return;

        }

        response.send(

                new MessageResponse(
                        "Post eliminado"
                )

        );

    }

}