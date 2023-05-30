package com.poncho.get_from_api_test.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ApiConnection {
    private static final String BASE_URL = "http://demo3005513.mockable.io/api/v1";
    private static final String GET_ALL_ID = "/entities/getAllIds";
    private static final String GET_BY_ID = "/object/";

    private static Gson gson;
    private OkHttpClient client;

    public ApiConnection() {
        client = new OkHttpClient();
        gson = new GsonBuilder().create();
    }

    public void getAllId(Callback callback) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(BASE_URL + GET_ALL_ID)).newBuilder();
        String url = urlBuilder.build().toString();
        buildRequest(url, callback);
    }

    public void getById(String id, Callback callback) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(BASE_URL + GET_BY_ID + id)).newBuilder();
        String url = urlBuilder.build().toString();
        buildRequest(url, callback);
    }

    public static Gson getGson() {
        return gson;
    }

    private void buildRequest(String urlString, Callback callback) {
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
