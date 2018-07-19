package com.ctandem.basemvvm.service.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ctandem.basemvvm.service.model.Post;

import java.util.List;

/**
 * Created by Zaeem Sattar on 7/18/2018.
 */
@Dao
public interface PostDao {

    @Insert
    void insert(Post post);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Post> post);

    @Query("DELETE FROM post")
    void deleteAll();

    @Query("SELECT * from post ORDER BY id ASC")
    LiveData<List<Post>> getAllPosts();
}
