package com.programacion.web.handler;

import com.programacion.web.model.Album;
import com.programacion.web.response.ErrorResponse;
import com.programacion.web.response.MessageResponse;
import com.programacion.web.service.AlbumService;
import com.programacion.web.util.CorsUtil;

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

        CorsUtil.apply(response);

        try {

            Album album =
                    request.content()
                            .as(Album.class);

            Album created =
                    service.create(album);

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

            Album album =
                    request.content()
                            .as(Album.class);

            Album updated =
                    service.update(
                            id,
                            album
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
                        "Album eliminado"
                )
        );

    }

}