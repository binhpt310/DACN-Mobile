package com.example.dacn.mucluc.Notification;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "noti")
public class Notification {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title,time1,time2,time3,daycn,day2,day3,day4,day5,day6,day7;

    public Notification(String title, String time1, String time2, String time3, String daycn, String day2, String day3, String day4, String day5, String day6, String day7) {
        this.title = title;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.daycn = daycn;
        this.day2 = day2;
        this.day3 = day3;
        this.day4 = day4;
        this.day5 = day5;
        this.day6 = day6;
        this.day7 = day7;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public String getDaycn() {
        return daycn;
    }

    public void setDaycn(String daycn) {
        this.daycn = daycn;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getDay3() {
        return day3;
    }

    public void setDay3(String day3) {
        this.day3 = day3;
    }

    public String getDay4() {
        return day4;
    }

    public void setDay4(String day4) {
        this.day4 = day4;
    }

    public String getDay5() {
        return day5;
    }

    public void setDay5(String day5) {
        this.day5 = day5;
    }

    public String getDay6() {
        return day6;
    }

    public void setDay6(String day6) {
        this.day6 = day6;
    }

    public String getDay7() {
        return day7;
    }

    public void setDay7(String day7) {
        this.day7 = day7;
    }
}
