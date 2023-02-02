package com.example.dacn.mucluc.Notification;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "noti")
public class Notification {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title,time1,time2,time3;
    private Boolean daycn,day2,day3,day4,day5,day6,day7,checkswitch;

    public Notification(String title, String time1, String time2, String time3, Boolean daycn, Boolean day2, Boolean day3, Boolean day4, Boolean day5, Boolean day6, Boolean day7, Boolean checkswitch) {
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
        this.checkswitch = checkswitch;
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

    public Boolean getDaycn() {
        return daycn;
    }

    public void setDaycn(Boolean daycn) {
        this.daycn = daycn;
    }

    public Boolean getDay2() {
        return day2;
    }

    public void setDay2(Boolean day2) {
        this.day2 = day2;
    }

    public Boolean getDay3() {
        return day3;
    }

    public void setDay3(Boolean day3) {
        this.day3 = day3;
    }

    public Boolean getDay4() {
        return day4;
    }

    public void setDay4(Boolean day4) {
        this.day4 = day4;
    }

    public Boolean getDay5() {
        return day5;
    }

    public void setDay5(Boolean day5) {
        this.day5 = day5;
    }

    public Boolean getDay6() {
        return day6;
    }

    public void setDay6(Boolean day6) {
        this.day6 = day6;
    }

    public Boolean getDay7() {
        return day7;
    }

    public void setDay7(Boolean day7) {
        this.day7 = day7;
    }

    public Boolean getCheckswitch() {
        return checkswitch;
    }

    public void setCheckswitch(Boolean checkswitch) {
        this.checkswitch = checkswitch;
    }
}
