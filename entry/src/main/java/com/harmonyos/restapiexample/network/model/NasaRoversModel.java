package com.harmonyos.restapiexample.network.model;

import com.squareup.moshi.Json;

import java.io.Serializable;
import java.util.List;

public class NasaRoversModel implements Serializable {

    @Json(name = "rovers")
    private List<RoverModel> rovers = null;

    public List<RoverModel> getRovers() {
        return rovers;
    }

}


