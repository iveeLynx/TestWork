package com.yaskovskiy.testwork.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Favorites {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name="id")
    public String id;

    @ColumnInfo(name = "imageUrl")
    public String imageUrl;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "url")
    public String url;



}
