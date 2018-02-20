package com.androidframework.feature.moviedetail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.androidframework.feature.movielist.MovieListRepository;
import com.androidframework.pojo.Movie;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yendang on 2/20/18.
 */

public class MovieDetailViewModel extends ViewModel {

    private MovieDetailRepository movieDetailRepository;
    private LiveData<Movie> movie;

    @Inject
    public MovieDetailViewModel(MovieDetailRepository movieDetailRepository) {
        this.movieDetailRepository = movieDetailRepository;
    }


    public void fetchMovieDetail(int movieId) {
        if (movie == null || movie.getValue() == null) {
            movie = movieDetailRepository.getMovieDetail(movieId);
        }
    }

    public LiveData<Movie> getMovie() {
        return movie;
    }


}
