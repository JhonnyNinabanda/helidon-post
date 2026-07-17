package com.programacion.web.handler;

import com.programacion.web.model.Album;
import com.programacion.web.response.ErrorResponse;
import com.programacion.web.response.MessageResponse;
import com.programacion.web.service.AlbumService;

import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

public class AlbumHandler {

    private final AlbumService service;

    public AlbumHandler(
            AlbumService service
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

        Album album =
                service.findById(id);

        if (album == null) {

            response.status(404);

            response.send(
                    new ErrorResponse(
                            "Album no encontrado"
                    )
            );

            return;

        }

        response.send(album);

    }

    public void create(
            ServerRequest request,
            ServerResponse response
    ) {

        Album album =
                request.content()
                        .as(Album.class);

        Album created =
                service.create(album);

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

        Album album =
                request.content()
                        .as(Album.class);

        response.send(
                service.update(
                        id,
                        album
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
                        "Album eliminado"
                )
        );

    }

}