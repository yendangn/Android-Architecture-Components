package com.androidframework.feature.movielist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.androidframework.pojo.Movie;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yendang on 2/18/18.
 */

public class MovieListViewModel extends ViewModel {

    private MovieListRepository movieListRepository;
    private LiveData<List<Movie>> movies;

    @Inject
    public MovieListViewModel(MovieListRepository movieListRepository) {
        this.movieListRepository = movieListRepository;
    }


    public void fetchMovies(boolean isRefresh) {
        if (isRefresh || movies == null || movies.getValue() == null || movies.getValue().isEmpty()) {
            movies = movieListRepository.getMovieList();
        }
    }


    public LiveData<List<Movie>> getMovies() {
        return movies;
    }
}
