package com.example.dacn.mucluc.Notification;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotiDAO {
    @Insert
    void insertNoti(Notification notification);

    @Query("SELECT * FROM noti")
    List<Notification> getListNoti();

    @Delete
    void deleteNoti(Notification notification);

    @Update
    void updateNoti(Notification notification);

    @Query("SELECT * FROM noti WHERE id =:id")
    Notification getSpecificNote(int id);
}
