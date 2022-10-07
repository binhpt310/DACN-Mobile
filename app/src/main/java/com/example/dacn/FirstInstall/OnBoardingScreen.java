package com.example.dacn.FirstInstall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;

import com.example.dacn.R;
import com.example.dacn.dangnhap.Logo_screen;
import com.example.dacn.mucluc.mucluc;
import com.example.dacn.trangchu2;

public class OnBoardingScreen extends AppCompatActivity {

    View onboarding_scr ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);
        onboarding_scr = findViewById(R.id.onboarding_scr_layout);

        onboarding_scr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardingScreen.this, Logo_screen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}