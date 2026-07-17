package com.programacion.web.handler;

import com.programacion.web.model.User;
import com.programacion.web.response.ErrorResponse;
import com.programacion.web.response.MessageResponse;
import com.programacion.web.service.UserService;

import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

public class UserHandler {

    private final UserService service;

    public UserHandler(
            UserService service
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

        User user =
                service.findById(id);

        if (user == null) {

            response.status(404);

            response.send(

                    new ErrorResponse(
                            "Usuario no encontrado"
                    )

            );

            return;

        }

        response.send(user);

    }

    public void create(
            ServerRequest request,
            ServerResponse response
    ) {

        User user =
                request.content()
                        .as(User.class);

        User created =
                service.create(user);

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

        User user =
                request.content()
                        .as(User.class);

        User updated =
                service.update(
                        id,
                        user
                );

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
                            "Usuario no encontrado"
                    )
            );

            return;

        }

        response.send(
                new MessageResponse(
                        "Usuario eliminado"
                )
        );

    }


}