package com.androidframework.service;

import com.androidframework.pojo.Movie;
import com.androidframework.pojo.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yendang on 2/18/18.
 */

public interface MovieService {

    @GET("movie/popular?")
    Call<MovieResult> getPopularMovies();

    @GET("movie/{movie_id}")
    Call<Movie> getMovieById(@Path("movie_id") int movieId);
}
