package com.androidframework.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by yendang on 2/23/18.
 */

@Dao
public interface BaseDao<T> {

//    @Query("SELECT * FROM T")
//    public abstract LiveData<List<T>> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(T... t);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(List<T> tList);

//    @Query("DELETE FROM T")
//    public abstract void deleteAll();

//    @Query("SELECT * FROM T where id = :id")
//    public abstract LiveData<T> findById(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long createIfNotExists(T t);
}
