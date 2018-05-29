package com.androidframework.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.androidframework.BuildConfig;
import com.androidframework.db.dao.SearchHistoryDao;
import com.androidframework.pojo.SearchHistory;

/**
 * Created by yendang on 2/23/18.
 */

@Database(entities = {SearchHistory.class}, version = BuildConfig.DB_VERSION)
public abstract class AppDb extends RoomDatabase {

    public abstract SearchHistoryDao searchHistoryDao();

}
