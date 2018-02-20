package com.androidframework.feature.moviedetail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.androidframework.core.APICallback;
import com.androidframework.pojo.Movie;
import com.androidframework.service.MovieService;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Created by yendang on 2/20/18.
 */

public class MovieDetailRepository {

    private MovieService movieService;

    @Inject
    public MovieDetailRepository(MovieService movieService) {
        this.movieService = movieService;
    }


    public LiveData<Movie> getMovieDetail(int movieId) {

        MutableLiveData<Movie> data = new MutableLiveData<>();


        movieService.getMovieById(movieId).enqueue(new APICallback<Movie>() {
            @Override
            public void onSuccess(Response<Movie> response) {
                data.setValue(response.body());
            }
        });

        return data;

    }

}
