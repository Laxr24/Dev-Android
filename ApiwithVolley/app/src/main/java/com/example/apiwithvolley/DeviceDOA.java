package com.example.apiwithvolley;


import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface  DeviceDOA {

    @Query("select * from Device")
    List<Device> getAll();

    @Query("select * from Device where lastUid=:lastUid")
    List<Device> getDevice(int lastUid);

    @Insert
    void setDevice(Device device);

    @Query("delete from Device")
    void deleteAll();
}
