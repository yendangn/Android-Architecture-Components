package com.androidframework.feature.movielist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.androidframework.core.APICallback;
import com.androidframework.pojo.Movie;
import com.androidframework.pojo.MovieResult;
import com.androidframework.service.MovieService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Created by yendang on 2/18/18.
 */

public class MovieListRepository {

    private MovieService movieService;

    @Inject
    public MovieListRepository(MovieService movieService) {
        this.movieService = movieService;
    }


    public LiveData<List<Movie>> getMovieList() {

        final MutableLiveData<List<Movie>> data = new MutableLiveData<>();


        movieService.getPopularMovies().enqueue(new APICallback<MovieResult>() {
            @Override
            public void onSuccess(Response<MovieResult> response) {
                data.setValue(response.body().getResults());
            }
        });

        return data;
    }


}
