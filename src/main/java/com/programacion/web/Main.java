package com.programacion.web;

import com.programacion.web.handler.AlbumHandler;
import com.programacion.web.handler.CommentHandler;
import com.programacion.web.handler.PhotoHandler;
import com.programacion.web.handler.PostHandler;
import com.programacion.web.handler.TodoHandler;
import com.programacion.web.handler.UserHandler;

import com.programacion.web.repository.AlbumRepository;
import com.programacion.web.repository.AlbumRepositoryImpl;

import com.programacion.web.repository.CommentRepository;
import com.programacion.web.repository.CommentRepositoryImpl;

import com.programacion.web.repository.PhotoRepository;
import com.programacion.web.repository.PhotoRepositoryImpl;

import com.programacion.web.repository.PostRepository;
import com.programacion.web.repository.PostRepositoryImpl;

import com.programacion.web.repository.TodoRepository;
import com.programacion.web.repository.TodoRepositoryImpl;

import com.programacion.web.repository.UserRepository;
import com.programacion.web.repository.UserRepositoryImpl;

import com.programacion.web.service.AlbumService;
import com.programacion.web.service.CommentService;
import com.programacion.web.service.PhotoService;
import com.programacion.web.service.PostService;
import com.programacion.web.service.TodoService;
import com.programacion.web.service.UserService;

import io.helidon.webserver.WebServer;
import io.helidon.http.HeaderNames;

public class Main {

    public static void main(String[] args) {

        // USERS

        UserRepository userRepository =
                new UserRepositoryImpl();

        UserService userService =
                new UserService(userRepository);

        UserHandler userHandler =
                new UserHandler(userService);

        // POSTS

        PostRepository postRepository =
                new PostRepositoryImpl();

        PostService postService =
                new PostService(
                        postRepository,
                        userService
                );

        PostHandler postHandler =
                new PostHandler(postService);

        // COMMENTS

        CommentRepository commentRepository =
                new CommentRepositoryImpl();

        CommentService commentService =
                new CommentService(
                        commentRepository,
                        postService
                );

        CommentHandler commentHandler =
                new CommentHandler(commentService);

        // ALBUMS

        AlbumRepository albumRepository =
                new AlbumRepositoryImpl();

        AlbumService albumService =
                new AlbumService(
                        albumRepository,
                        userService
                );

        AlbumHandler albumHandler =
                new AlbumHandler(albumService);

        // PHOTOS

        PhotoRepository photoRepository =
                new PhotoRepositoryImpl();

        PhotoService photoService =
                new PhotoService(
                        photoRepository,
                        albumService
                );

        PhotoHandler photoHandler =
                new PhotoHandler(photoService);

        // TODOS

        TodoRepository todoRepository =
                new TodoRepositoryImpl();

        TodoService todoService =
                new TodoService(
                        todoRepository,
                        userService
                );

        TodoHandler todoHandler =
                new TodoHandler(todoService);

        WebServer server =
                WebServer.builder()

                        .port(8080)

                        .routing(routing -> {

                            // USERS

                            routing.get("/api/users", userHandler::findAll);
                            routing.get("/api/users/{id}", userHandler::findById);
                            routing.post("/api/users", userHandler::create);
                            routing.put("/api/users/{id}", userHandler::update);
                            routing.delete("/api/users/{id}", userHandler::delete);

                            // POSTS

                            routing.get("/api/posts", postHandler::findAll);
                            routing.get("/api/posts/{id}", postHandler::findById);
                            routing.post("/api/posts", postHandler::create);
                            routing.put("/api/posts/{id}", postHandler::update);
                            routing.delete("/api/posts/{id}", postHandler::delete);

                            // COMMENTS

                            routing.get("/api/comments", commentHandler::findAll);
                            routing.get("/api/comments/{id}", commentHandler::findById);
                            routing.post("/api/comments", commentHandler::create);
                            routing.put("/api/comments/{id}", commentHandler::update);
                            routing.delete("/api/comments/{id}", commentHandler::delete);

                            // ALBUMS

                            routing.get("/api/albums", albumHandler::findAll);
                            routing.get("/api/albums/{id}", albumHandler::findById);
                            routing.post("/api/albums", albumHandler::create);
                            routing.put("/api/albums/{id}", albumHandler::update);
                            routing.delete("/api/albums/{id}", albumHandler::delete);

                            // PHOTOS

                            routing.get("/api/photos", photoHandler::findAll);
                            routing.get("/api/photos/{id}", photoHandler::findById);
                            routing.post("/api/photos", photoHandler::create);
                            routing.put("/api/photos/{id}", photoHandler::update);
                            routing.delete("/api/photos/{id}", photoHandler::delete);

                            // TODOS

                            routing.get("/api/todos", todoHandler::findAll);
                            routing.get("/api/todos/{id}", todoHandler::findById);
                            routing.post("/api/todos", todoHandler::create);
                            routing.put("/api/todos/{id}", todoHandler::update);
                            routing.delete("/api/todos/{id}", todoHandler::delete);

                        })

                        .build();

        server.start();

        System.out.println(
                "Servidor iniciado en puerto 8080"
        );

    }

}