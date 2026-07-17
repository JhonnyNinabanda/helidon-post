package com.programacion.web.util;

import io.helidon.webserver.http.ServerResponse;

public final class CorsUtil {

    private CorsUtil() {
    }

    public static void apply(
            ServerResponse response
    ) {

        response.header(
                "Access-Control-Allow-Origin",
                "http://localhost:5173"
        );

        response.header(
                "Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS"
        );

        response.header(
                "Access-Control-Allow-Headers",
                "Content-Type"
        );

    }

}
