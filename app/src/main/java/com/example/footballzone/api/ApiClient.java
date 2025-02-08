package com.example.footballzone.api;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("X-Auth-Token", "19e70883f1c142b795aaf278204daf3c") // ⬅️ Замените API-ключ
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                })
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(" https://api.football-data.org/v4/")  // ✅ Должно заканчиваться на /
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

