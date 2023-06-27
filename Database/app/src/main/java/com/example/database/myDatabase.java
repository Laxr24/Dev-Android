package com.example.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, exportSchema = false, version = 2)
public abstract class myDatabase extends RoomDatabase {

    public static myDatabase instance;

    public static myDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context, myDatabase.class, "user_db")
                    .enableMultiInstanceInvalidation()
                    .fallbackToDestructiveMigration()
//                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
}
