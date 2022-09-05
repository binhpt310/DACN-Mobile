package com.example.dacn.dangnhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dacn.R;

public class dang_ky extends AppCompatActivity {

    Button btn_dangky;
    TextView txt_dangnhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        btn_dangky = findViewById(R.id.btn_dangky);
        txt_dangnhap = findViewById(R.id.dangnhapotrangdangky);

        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dang_ky.this, dang_nhap.class);
                startActivity(intent);
                finish();
            }
        });

        txt_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dang_ky.this, dang_nhap.class);
                startActivity(intent);
                finish();
            }
        });
    }
}