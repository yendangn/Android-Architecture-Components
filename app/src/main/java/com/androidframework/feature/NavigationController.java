package com.androidframework.feature;

import android.support.v4.app.FragmentManager;

import com.androidframework.R;
import com.androidframework.feature.moviedetail.MovieDetailFragment;
import com.androidframework.feature.movielist.MovieListFragment;

import javax.inject.Inject;

/**
 * Created by yendang on 2/19/18.
 */

public class NavigationController {

    private final int containerId;
    private final FragmentManager fragmentManager;

    @Inject
    public NavigationController(MainActivity mainActivity) {
        containerId = R.id.container;
        fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateToMovieListFragment() {
        MovieListFragment fragment = new MovieListFragment();
        fragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .setReorderingAllowed(true)
                .commitAllowingStateLoss();
    }

    public void navigateToMovieDetailFragment(int movieId) {
        MovieDetailFragment fragment = MovieDetailFragment.newInstance(movieId);
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(containerId, fragment)
                .addToBackStack("MovieDetailFragment")
                .commitAllowingStateLoss();
    }

}
