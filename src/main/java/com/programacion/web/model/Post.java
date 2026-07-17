package com.programacion.web.model;

public record Post(

        Integer id,
        Integer userId,
        String title,
        String body

) {
}