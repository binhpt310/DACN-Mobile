package com.example.dacn.mucluc.Notification;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Notification.class}, version = 1)
public abstract class NotiDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "noti.db";
    private static NotiDatabase instance;

    public static synchronized NotiDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),NotiDatabase.class,DATABASE_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract NotiDAO notiDAO();
}
