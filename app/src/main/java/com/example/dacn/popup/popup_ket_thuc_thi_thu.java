package com.example.dacn.popup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.dacn.R;
import com.example.dacn.hoanthanhbai.hoanthanhbaithi;
import com.example.dacn.trangchu2;

public class popup_ket_thuc_thi_thu extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_ket_thuc_thi_thu);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        int Width = dm.widthPixels;
        int Height = dm.heightPixels;

        getWindow().setLayout((int)(Width*.9), (int)(Height*.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;
        getWindow().setAttributes(params);

        Button btn_trangchu = findViewById(R.id.button_trangchu);
        Button btn_ketqua = findViewById(R.id.button_ketqua);

        btn_ketqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(popup_ket_thuc_thi_thu.this, hoanthanhbaithi.class);
                startActivity(intent);
            }
        });

        btn_trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(popup_ket_thuc_thi_thu.this, trangchu2.class);
                startActivity(intent);
            }
        });
    }


}