package com.example.dbview;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDao {

    @Query("select * from user")
    List<User> fetchAll();

    @Insert
    void insert(User user);

    @Query("delete from user")
    void deleteAll();

    @Query("delete from user where uid=:uid")
    void deleteUser(int uid);
}
