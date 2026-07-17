package com.programacion.web.repository;

import com.programacion.web.config.DbClientProvider;
import com.programacion.web.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl
        implements UserRepository {

    @Override
    public List<User> findAll() {

        List<User> users =
                new ArrayList<>();

        DbClientProvider.getDbClient()
                .execute()
                .query(
                        """
                        SELECT *
                        FROM users
                        ORDER BY id
                        """
                )
                .forEach(row -> {

                    users.add(

                            new User(

                                    row.column("id")
                                            .as(Integer.class)
                                            .orElse(null),

                                    row.column("name")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("username")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("email")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("address_street")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("address_suite")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("address_city")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("address_zipcode")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("address_geo_lat")
                                            .as(BigDecimal.class)
                                            .orElse(null),

                                    row.column("address_geo_lng")
                                            .as(BigDecimal.class)
                                            .orElse(null),

                                    row.column("phone")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("website")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("company_name")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("company_catch_phrase")
                                            .as(String.class)
                                            .orElse(null),

                                    row.column("company_bs")
                                            .as(String.class)
                                            .orElse(null)

                            )

                    );

                });

        return users;

    }

    @Override
    public User findById(
            Integer id
    ) {

        return DbClientProvider.getDbClient()
                .execute()
                .query(
                        """
                        SELECT *
                        FROM users
                        WHERE id = ?
                        """,
                        id
                )
                .findFirst()
                .map(row ->

                        new User(

                                row.column("id")
                                        .as(Integer.class)
                                        .orElse(null),

                                row.column("name")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("username")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("email")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("address_street")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("address_suite")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("address_city")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("address_zipcode")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("address_geo_lat")
                                        .as(BigDecimal.class)
                                        .orElse(null),

                                row.column("address_geo_lng")
                                        .as(BigDecimal.class)
                                        .orElse(null),

                                row.column("phone")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("website")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("company_name")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("company_catch_phrase")
                                        .as(String.class)
                                        .orElse(null),

                                row.column("company_bs")
                                        .as(String.class)
                                        .orElse(null)

                        )

                )
                .orElse(null);

    }

    @Override
    public User create(
            User user
    ) {

        DbClientProvider.getDbClient()
                .execute()
                .insert(
                        """
                        INSERT INTO users
                        (
                            id,
                            name,
                            username,
                            email,
                            address_street,
                            address_suite,
                            address_city,
                            address_zipcode,
                            address_geo_lat,
                            address_geo_lng,
                            phone,
                            website,
                            company_name,
                            company_catch_phrase,
                            company_bs
                        )
                        VALUES
                        (
                            ?, ?, ?, ?, ?, ?, ?, ?,
                            ?, ?, ?, ?, ?, ?, ?
                        )
                        """,

                        user.id(),
                        user.name(),
                        user.username(),
                        user.email(),
                        user.addressStreet(),
                        user.addressSuite(),
                        user.addressCity(),
                        user.addressZipcode(),
                        user.addressGeoLat(),
                        user.addressGeoLng(),
                        user.phone(),
                        user.website(),
                        user.companyName(),
                        user.companyCatchPhrase(),
                        user.companyBs()
                );

        return user;

    }

    @Override
    public User update(
            Integer id,
            User user
    ) {

        DbClientProvider.getDbClient()
                .execute()
                .update(
                        """
                        UPDATE users
                        SET
                            name=?,
                            username=?,
                            email=?,
                            address_street=?,
                            address_suite=?,
                            address_city=?,
                            address_zipcode=?,
                            address_geo_lat=?,
                            address_geo_lng=?,
                            phone=?,
                            website=?,
                            company_name=?,
                            company_catch_phrase=?,
                            company_bs=?
                        WHERE id=?
                        """,

                        user.name(),
                        user.username(),
                        user.email(),
                        user.addressStreet(),
                        user.addressSuite(),
                        user.addressCity(),
                        user.addressZipcode(),
                        user.addressGeoLat(),
                        user.addressGeoLng(),
                        user.phone(),
                        user.website(),
                        user.companyName(),
                        user.companyCatchPhrase(),
                        user.companyBs(),
                        id
                );

        return findById(id);

    }

    @Override
    public boolean delete(
            Integer id
    ) {

        DbClientProvider.getDbClient()
                .execute()
                .delete(
                        """
                        DELETE FROM users
                        WHERE id = ?
                        """,
                        id
                );

        return true;

    }

    @Override
    public boolean existsById(
            Integer id
    ) {

        return DbClientProvider.getDbClient()
                .execute()
                .query(
                        """
                        SELECT id
                        FROM users
                        WHERE id = ?
                        """,
                        id
                )
                .findFirst()
                .isPresent();

    }

}