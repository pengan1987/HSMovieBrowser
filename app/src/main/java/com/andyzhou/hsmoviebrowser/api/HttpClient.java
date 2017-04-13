package com.andyzhou.hsmoviebrowser.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andyzhou on 2017-04-13.
 */

public class HttpClient {
    public MovieApiService movieApiService;
    public static String apiKey = "5b2b06134bae31ee8534bd84fcf59eea";

    private static final HttpClient httpClient = new HttpClient();

    public static HttpClient getInstance() {
        return httpClient;
    }

    private String imageBaseUrl;

    private HttpClient() {
        init();
    }

    private void init() {
        String baseUrl = "https://api.themoviedb.org";

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit restAdapter = builder.client(client)
                .baseUrl(baseUrl)
                .build();

        movieApiService = restAdapter.create(MovieApiService.class);
    }

    public String getImageBaseUrl() {
        return imageBaseUrl;
    }

    public void setImageBaseUrl(String imageBaseUrl) {
        this.imageBaseUrl = imageBaseUrl;
    }
}
