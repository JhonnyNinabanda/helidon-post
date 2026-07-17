package com.programacion.web.model;

public record Todo(

        Integer id,
        Integer userId,
        String title,
        Boolean completed

) {
}