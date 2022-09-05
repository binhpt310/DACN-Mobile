package com.example.dacn.dangnhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dacn.R;
import com.example.dacn.trangchu2;

public class dang_nhap extends AppCompatActivity {

    Button btn_dangnhap;
    TextView txt_dangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        txt_dangky = findViewById(R.id.btn_dangkyotrangdangnhap);

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dang_nhap.this, trangchu2.class);
                startActivity(intent);
                finish();
            }
        });

        txt_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dang_nhap.this, dang_ky.class);
                startActivity(intent);
                finish();
            }
        });
    }
}