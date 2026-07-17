package com.programacion.web;

import com.programacion.web.handler.UserHandler;
import com.programacion.web.repository.UserRepository;
import com.programacion.web.repository.UserRepositoryImpl;
import com.programacion.web.service.UserService;

import io.helidon.webserver.WebServer;

public class Main {

    public static void main(String[] args) {

        UserRepository repository =
                new UserRepositoryImpl();

        UserService service =
                new UserService(
                        repository
                );

        UserHandler handler =
                new UserHandler(
                        service
                );

        WebServer server =
                WebServer.builder()

                        .port(8080)

                        .routing(routing -> {

                            routing.get(
                                    "/api/users",
                                    handler::findAll
                            );

                            routing.get(
                                    "/api/users/{id}",
                                    handler::findById
                            );

                            routing.post(
                                    "/api/users",
                                    handler::create
                            );

                            routing.put(
                                    "/api/users/{id}",
                                    handler::update
                            );

                            routing.delete(
                                    "/api/users/{id}",
                                    handler::delete
                            );

                        })

                        .build();

        server.start();

        System.out.println(
                "Servidor iniciado en puerto 8080"
        );

    }

}