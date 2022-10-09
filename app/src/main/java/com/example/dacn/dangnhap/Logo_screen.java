package com.example.dacn.dangnhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.dacn.FirstInstall.MySharedPreferences;
import com.example.dacn.FirstInstall.OnBoardingScreen;
import com.example.dacn.R;
import com.example.dacn.dangnhap.Connectivity;
import com.example.dacn.onboarding.waiting;

public class Logo_screen extends AppCompatActivity {

    int SPLASH_SCREEN = 2000;
    private static final String key_first_install = "KEY_FIRST_INSTALL";

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

    }

    @Override
    protected void onStart() {
        super.onStart();
        Context context = getApplicationContext();

        if (Connectivity.isConnected(context)) {
            final MySharedPreferences mySharedPreferences = new MySharedPreferences(this);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mySharedPreferences.getBooleanVal(key_first_install)) {
                        Intent intent = new Intent(Logo_screen.this, dang_nhap.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(Logo_screen.this, waiting.class);
                        mySharedPreferences.putBooleanVal(key_first_install, true);
                        startActivity(intent);
                    }
                }
            }, SPLASH_SCREEN);
        }

        else
            Toast.makeText(context, "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
    }










}