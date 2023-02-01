package com.example.dacn.quenmatkhau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dacn.R;
import com.example.dacn.dangnhap.dang_nhap;

public class quenmatkhau4 extends AppCompatActivity {

    Button btn_quaylai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau4);

        btn_quaylai = findViewById(R.id.btn_quaylai);
        btn_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(quenmatkhau4.this, dang_nhap.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                finish();
                finish();
            }
        });
    }
}