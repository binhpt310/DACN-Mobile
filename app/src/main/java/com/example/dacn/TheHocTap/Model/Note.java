package com.example.dacn.TheHocTap.Model;

import android.graphics.Color;


import com.example.dacn.TheHocTap.Util.GeneralUtil;

import java.io.Serializable;

public class Note implements Serializable {
    private boolean favorite;
    private String title;
    private String content;
    private String date;
    private int color;

    public static Note createWithTitleAndContent(String title, String content){
        return new Note(title, content, Color.WHITE, GeneralUtil.currentDate(), false);
    }

    public static Note createWithContent(String content){
        return new Note("", content, Color.WHITE, GeneralUtil.currentDate(), false);
    }

    public Note(String title, String content, int color, String date, boolean favorite){
        this.title = title;
        this.content = content;
        this.date = date;
        this.color = color;
        this.favorite = favorite;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public String getDate() {
        return date;
    }

    public int getColor() {
        return color;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if (!Note.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final Note b = (Note) obj;

        return (this.getTitle().equals(b.getTitle()) && this.getContent().equals(b.getContent()) &&
                this.getDate().equals(b.getDate()) && this.getColor() == b.getColor());
    }



}
