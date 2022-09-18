package com.example.dacn.FirstInstall;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    private static final String my_shared_pre = "MY_SHARE_PREFERENCES";
    private Context context;

    public MySharedPreferences(Context context) {
        this.context = context;
    }

    public void putBooleanVal(String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(my_shared_pre,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public boolean getBooleanVal(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(my_shared_pre,0);
        return sharedPreferences.getBoolean(key, false);
    }

}
