package com.example.dacn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.AnimationUtils;

public class Logo_screen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;

    ImageView logo;
    TextView txtTenApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_logo_screen);

        final Animation anim_tren = AnimationUtils.loadAnimation(this, R.anim.tren_xuong);
        final Animation anim_duoi = AnimationUtils.loadAnimation(this, R.anim.duoi_len);

        logo = findViewById(R.id.logo);
        txtTenApp = findViewById(R.id.txt_tenapp);

        logo.setAnimation(anim_tren);
        txtTenApp.setAnimation(anim_duoi);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Logo_screen.this, dang_nhap.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }










}