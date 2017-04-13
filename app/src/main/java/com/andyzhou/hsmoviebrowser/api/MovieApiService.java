package com.andyzhou.hsmoviebrowser.api;

import com.andyzhou.hsmoviebrowser.models.ApiConfiguration;
import com.andyzhou.hsmoviebrowser.models.MovieRequest;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by andyzhou on 2017-04-13.
 */

public interface MovieApiService {
    @GET("/3/search/movie")
    Observable<MovieRequest> getMovies(@Query("query") String keyword,
                                       @Query("api_key") String movieApiKey,
                                       @Query("page") Integer pageNumber);
    @GET("/3/configuration")
    Observable<ApiConfiguration> getConfig(@Query("api_key") String movieApiKey);
}
