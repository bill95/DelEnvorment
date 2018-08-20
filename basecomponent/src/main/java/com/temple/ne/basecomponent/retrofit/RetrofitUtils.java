package com.temple.ne.basecomponent.retrofit;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static volatile Retrofit retrofit;

    private RetrofitUtils() {

    }

    private static void init() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
        retrofit = new Retrofit.Builder().baseUrl("http://app-api.test.ddanglc.com/").addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
//        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(DecryptConverterFactory.create()).build();

    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            init();
        }
        return retrofit;
    }

    public static Api getApi() {
        if (retrofit == null) {
            init();
        }
        return retrofit.create(Api.class);

    }
}
