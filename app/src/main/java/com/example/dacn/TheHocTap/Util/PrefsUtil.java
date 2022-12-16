package com.example.dacn.TheHocTap.Util;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

import com.example.dacn.TheHocTap.Model.Note;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;

public class PrefsUtil extends ContextWrapper {

    public PrefsUtil(Context base) {
        super(base);
    }

    // Saves the ArrayList using Gson
    public static void saveNotes(ArrayList<Note> list, String key, Context context){
        SharedPreferences prefs = context.getSharedPreferences("NOTES", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    // Returns the ArrayList from sharedprefs
    public static ArrayList<Note> getNotes(String key, Context context){
        SharedPreferences prefs = context.getSharedPreferences("NOTES", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<Note>>() {}.getType();
        if (gson.fromJson(json, type) == null) return new ArrayList<>();
        return gson.fromJson(json, type);
    }
}
