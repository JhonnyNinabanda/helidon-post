plugins {
    java
    application
}

group = "com.programacion.web"
version = "1.0"

repositories {
    mavenCentral()
}

val helidonVersion = "4.5.0"

dependencies {

    implementation(
        platform(
            "io.helidon:helidon-bom:$helidonVersion"
        )
    )

    implementation(
        "io.helidon.webserver:helidon-webserver"
    )

    implementation(
        "io.helidon.http.media:helidon-http-media-jsonb"
    )

    implementation(
        "io.helidon.config:helidon-config-yaml"
    )

    implementation(
        "io.helidon.dbclient:helidon-dbclient"
    )

    implementation(
        "io.helidon.dbclient:helidon-dbclient-jdbc"
    )

    implementation(
        "io.helidon.dbclient:helidon-dbclient-hikari"
    )

    implementation(
        "org.postgresql:postgresql:42.7.7"
    )

    implementation(
        "io.helidon.webserver:helidon-webserver-cors"
    )

}

java {

    toolchain {

        languageVersion.set(
            JavaLanguageVersion.of(21)
        )

    }

}

application {

    mainClass.set(
        "com.programacion.web.Main"
    )

}