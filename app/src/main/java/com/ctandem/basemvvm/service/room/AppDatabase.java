package com.ctandem.basemvvm.service.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.ctandem.basemvvm.service.model.Post;

/**
 * Created by Zaeem Sattar on 7/18/2018.
 */
@Database(entities = {Post.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "post_database";
    private static AppDatabase INSTANCE;

    public abstract PostDao postDao();


    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            AppDatabase.class, DB_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
