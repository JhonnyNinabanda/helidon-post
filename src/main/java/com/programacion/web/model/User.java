package com.programacion.web.model;

import java.math.BigDecimal;

public record User(
        Integer id,
        String name,
        String username,
        String email,
        String addressStreet,
        String addressSuite,
        String addressCity,
        String addressZipcode,
        BigDecimal addressGeoLat,
        BigDecimal addressGeoLng,
        String phone,
        String website,
        String companyName,
        String companyCatchPhrase,
        String companyBs
) {
}