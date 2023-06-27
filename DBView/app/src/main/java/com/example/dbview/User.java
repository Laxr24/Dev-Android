package com.example.dbview;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class User {

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "imageID")
    private int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ColumnInfo(name = "userName")
    private String name;

    public User(int image, String name) {
        this.image = image;
        this.name = name;
    }

    @Ignore
    public User(int image, String name, int uid){
        this.image = image;
        this.name = name;
        this.uid = uid;
    }
}
