package com.programacion.web.config;

import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;

public final class DbClientProvider {

    private static final DbClient DB_CLIENT;

    static {

        Config config =
                Config.create();

        DB_CLIENT =
                DbClient.builder()
                        .config(
                                config.get("db")
                        )
                        .build();

    }

    private DbClientProvider() {
    }

    public static DbClient getDbClient() {

        return DB_CLIENT;

    }

}