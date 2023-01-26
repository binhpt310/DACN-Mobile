package com.example.dacn.mucluc.Notification;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotiDAO {
    @Insert
    void insertNoti(Notification notification);

    @Query("SELECT * FROM noti")
    List<Notification> getListNoti();
}
