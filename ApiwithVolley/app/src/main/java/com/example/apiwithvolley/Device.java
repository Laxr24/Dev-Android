package com.example.apiwithvolley;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Device {
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "lastUid")
    private int lastUid;

    public int getLastUid() {
        return lastUid;
    }

    public void setLastUid(int lastUid) {
        this.lastUid = lastUid;
    }

    public Device(int uid, String title, String description) {
        this.uid = uid;
        this.title = title;
        this.description = description;
    }

    @Ignore
    public Device(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Ignore
    public Device(String title, String description, int lastUid) {
        this.title = title;
        this.description = description;
        this.lastUid = lastUid;
    }
}
