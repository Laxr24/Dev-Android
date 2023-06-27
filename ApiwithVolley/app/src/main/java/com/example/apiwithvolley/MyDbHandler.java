package com.example.apiwithvolley;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Device.class}, version = 1, exportSchema = false)
public abstract class MyDbHandler extends RoomDatabase {
    public static MyDbHandler instance;

    public static synchronized MyDbHandler getDB(Context context) {
        if(MyDbHandler.instance == null){
            instance = Room.databaseBuilder(context, MyDbHandler.class, "devices")
                    .enableMultiInstanceInvalidation()
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract DeviceDOA databaseAccessor();
}
