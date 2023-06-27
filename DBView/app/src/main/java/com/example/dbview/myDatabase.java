package com.example.dbview;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Database;


@Database(entities = {User.class}, exportSchema = false, version = 1)
public abstract class myDatabase extends RoomDatabase {
    private static myDatabase instance;
    public static myDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context, myDatabase.class, "users")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .enableMultiInstanceInvalidation()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
}
