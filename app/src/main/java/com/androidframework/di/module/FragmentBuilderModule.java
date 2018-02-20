package com.androidframework.di.module;

import com.androidframework.feature.moviedetail.MovieDetailFragment;
import com.androidframework.feature.movielist.MovieListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by yendang on 2/18/18.
 */

@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract MovieListFragment contributeMovieListFragment();

    @ContributesAndroidInjector
    abstract MovieDetailFragment contributeMovieDetailFragment();

}
