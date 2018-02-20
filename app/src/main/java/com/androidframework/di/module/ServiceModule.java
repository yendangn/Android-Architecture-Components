package com.androidframework.di.module;

import com.androidframework.service.MovieService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by yendang on 2/18/18.
 */

@Module(includes = NetworkModule.class)
public class ServiceModule {

    @Singleton
    @Provides
    MovieService provideMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    //Provide other services

}
