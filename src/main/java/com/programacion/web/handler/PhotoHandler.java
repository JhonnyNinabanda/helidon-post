package com.programacion.web.handler;

import com.programacion.web.model.Photo;
import com.programacion.web.response.ErrorResponse;
import com.programacion.web.response.MessageResponse;
import com.programacion.web.service.PhotoService;
import com.programacion.web.util.CorsUtil;

import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

public class PhotoHandler {

    private final PhotoService service;

    public PhotoHandler(
            PhotoService service
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

        Photo photo =
                service.findById(id);

        if (photo == null) {

            response.status(404);

            response.send(
                    new ErrorResponse(
                            "Photo no encontrada"
                    )
            );

            return;

        }

        response.send(photo);

    }

    public void create(
            ServerRequest request,
            ServerResponse response
    ) {

        CorsUtil.apply(response);

        try {

            Photo photo =
                    request.content()
                            .as(Photo.class);

            Photo created =
                    service.create(photo);

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

            Photo photo =
                    request.content()
                            .as(Photo.class);

            Photo updated =
                    service.update(
                            id,
                            photo
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
                        "Photo eliminada"
                )
        );

    }

}