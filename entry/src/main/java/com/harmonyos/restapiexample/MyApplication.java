package com.harmonyos.restapiexample;

import com.harmonyos.restapiexample.network.APIServices;
import ohos.aafwk.ability.AbilityPackage;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.util.concurrent.TimeUnit;

public class MyApplication extends AbilityPackage {
    public static APIServices createRetrofitClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60L, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create()).client(client)
                .build();
        return retrofit.create(APIServices.class);
    }

    @Override
    public void onInitialize() {
        super.onInitialize();
    }
}
