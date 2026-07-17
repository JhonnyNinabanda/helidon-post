package com.programacion.web.handler;

import com.programacion.web.model.Photo;
import com.programacion.web.response.ErrorResponse;
import com.programacion.web.response.MessageResponse;
import com.programacion.web.service.PhotoService;

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

        Photo photo =
                request.content()
                        .as(Photo.class);

        Photo created =
                service.create(photo);

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

        Photo photo =
                request.content()
                        .as(Photo.class);

        response.send(
                service.update(
                        id,
                        photo
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
                        "Photo eliminada"
                )
        );

    }

}