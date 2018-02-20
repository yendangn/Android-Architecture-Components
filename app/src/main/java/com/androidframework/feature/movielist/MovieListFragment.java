package com.androidframework.feature.movielist;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidframework.R;
import com.androidframework.core.BaseFragment;
import com.androidframework.feature.NavigationController;
import com.androidframework.pojo.Movie;
import com.androidframework.util.GridSpacingItemDecoration;
import com.androidframework.util.UiUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends BaseFragment {

    @Inject
    NavigationController navigationController;

    @BindView(R.id.rvMovie)
    RecyclerView rvMovie;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;


    private MovieListViewModel movieListViewModel;

    private MovieListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        movieListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MovieListViewModel.class);

        setUpAdapter();
        setUpSwipeRefreshLayout();

        getMovies(false);

        observerMovies();
    }

    private void setUpSwipeRefreshLayout() {

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                getMovies(true);
            }
        });
    }

    private void setUpAdapter() {

        int numOfCol = 3;

        rvMovie.addItemDecoration(new GridSpacingItemDecoration(numOfCol, UiUtil.getPixelFromDp(5), true));

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), numOfCol);
        rvMovie.setLayoutManager(layoutManager);

        adapter = new MovieListAdapter(getContext(), numOfCol);

        adapter.setmMovieClickCallback(movie -> {
            navigationController.navigateToMovieDetailFragment(movie.getId());

        });

        rvMovie.setAdapter(adapter);

    }

    private void getMovies(boolean isRefresh) {
        movieListViewModel.fetchMovies(isRefresh);
    }

    private void observerMovies() {
        movieListViewModel.getMovies().observe(this, movies -> {
            adapter.setmMovieList(movies);
        });
    }
}
