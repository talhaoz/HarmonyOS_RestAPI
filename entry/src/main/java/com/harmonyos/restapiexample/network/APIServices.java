package com.harmonyos.restapiexample.network;

import com.harmonyos.restapiexample.network.model.NasaRoversModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface APIServices {

    @GET("rovers")
    Observable<NasaRoversModel> getNasaRovers(@Query("api_key") String apiKey);

}
