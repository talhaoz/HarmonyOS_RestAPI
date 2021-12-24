package com.harmonyos.restapiexample.network.model;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class RoverModel implements Serializable {

    @Json(name = "name")
    private String name;
    @Json(name = "landing_date")
    private String landingDate;

    public String getName() {
        return name;
    }

    public String getLandingDate() {
        return landingDate;
    }
}