package com.androidframework.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.androidframework.BuildConfig;
import com.androidframework.db.AppDb;
import com.androidframework.db.dao.SearchHistoryDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yendang on 2/23/18.
 */

@Module
public class DbModule {

    @Singleton
    @Provides
    AppDb providesRoomDatabase(Application app) {
        return Room.databaseBuilder(app, AppDb.class, BuildConfig.DB_NAME).build();
    }

    @Singleton
    @Provides
    SearchHistoryDao provideSearchHistoryDao(AppDb db) {
        return db.searchHistoryDao();
    }
}
