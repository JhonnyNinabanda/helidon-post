package com.programacion.web;

import com.programacion.web.handler.CommentHandler;
import com.programacion.web.handler.PostHandler;
import com.programacion.web.handler.UserHandler;

import com.programacion.web.repository.CommentRepository;
import com.programacion.web.repository.CommentRepositoryImpl;

import com.programacion.web.repository.PostRepository;
import com.programacion.web.repository.PostRepositoryImpl;

import com.programacion.web.repository.UserRepository;
import com.programacion.web.repository.UserRepositoryImpl;

import com.programacion.web.service.CommentService;
import com.programacion.web.service.PostService;
import com.programacion.web.service.UserService;

import io.helidon.webserver.WebServer;

public class Main {

    public static void main(String[] args) {

        // USERS

        UserRepository userRepository =
                new UserRepositoryImpl();

        UserService userService =
                new UserService(
                        userRepository
                );

        UserHandler userHandler =
                new UserHandler(
                        userService
                );

        // POSTS

        PostRepository postRepository =
                new PostRepositoryImpl();

        PostService postService =
                new PostService(
                        postRepository
                );

        PostHandler postHandler =
                new PostHandler(
                        postService
                );

        // COMMENTS

        CommentRepository commentRepository =
                new CommentRepositoryImpl();

        CommentService commentService =
                new CommentService(
                        commentRepository
                );

        CommentHandler commentHandler =
                new CommentHandler(
                        commentService
                );

        WebServer server =
                WebServer.builder()

                        .port(8080)

                        .routing(routing -> {

                            // USERS

                            routing.get(
                                    "/api/users",
                                    userHandler::findAll
                            );

                            routing.get(
                                    "/api/users/{id}",
                                    userHandler::findById
                            );

                            routing.post(
                                    "/api/users",
                                    userHandler::create
                            );

                            routing.put(
                                    "/api/users/{id}",
                                    userHandler::update
                            );

                            routing.delete(
                                    "/api/users/{id}",
                                    userHandler::delete
                            );

                            // POSTS

                            routing.get(
                                    "/api/posts",
                                    postHandler::findAll
                            );

                            routing.get(
                                    "/api/posts/{id}",
                                    postHandler::findById
                            );

                            routing.post(
                                    "/api/posts",
                                    postHandler::create
                            );

                            routing.put(
                                    "/api/posts/{id}",
                                    postHandler::update
                            );

                            routing.delete(
                                    "/api/posts/{id}",
                                    postHandler::delete
                            );

                            // COMMENTS

                            routing.get(
                                    "/api/comments",
                                    commentHandler::findAll
                            );

                            routing.get(
                                    "/api/comments/{id}",
                                    commentHandler::findById
                            );

                            routing.post(
                                    "/api/comments",
                                    commentHandler::create
                            );

                            routing.put(
                                    "/api/comments/{id}",
                                    commentHandler::update
                            );

                            routing.delete(
                                    "/api/comments/{id}",
                                    commentHandler::delete
                            );

                        })

                        .build();

        server.start();

        System.out.println(
                "Servidor iniciado en puerto 8080"
        );

    }

}