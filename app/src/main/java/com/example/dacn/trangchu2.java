package com.example.dacn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.mucluc.mucluc;

public class trangchu2 extends AppCompatActivity {

    ImageView img_mucluc;
    LinearLayout lsu,dia,gdcd,anhvan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu2);

        //View thi = findViewById(R.id.lichsu);

        khaibao();

        img_mucluc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(trangchu2.this, mucluc.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
            }
        });

        /*thi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), bo_de_thi.class);
                startActivity(intent);
            }
        });*/

        lsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TruyenDuLieu.trMon="His";
                TruyenDuLieu.trTenMon = "Lịch sử";
                Intent intent = new Intent(trangchu2.this, bo_de_thi.class);
                startActivity(intent);
            }
        });

        dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TruyenDuLieu.trMon="Geo";
                TruyenDuLieu.trTenMon = "Địa lý";
                Intent intent = new Intent(trangchu2.this, bo_de_thi.class);
                startActivity(intent);
            }
        });

        anhvan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TruyenDuLieu.trMon="Eng";
                TruyenDuLieu.trTenMon = "Anh văn";
                Intent intent = new Intent(trangchu2.this, bo_de_thi.class);
                startActivity(intent);
            }
        });

        gdcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TruyenDuLieu.trMon="Gdcd";
                TruyenDuLieu.trTenMon = "GDCD";
                Intent intent = new Intent(trangchu2.this, bo_de_thi.class);
                startActivity(intent);
            }
        });

    }

    private void khaibao() {
        img_mucluc = findViewById(R.id.img_mucluc);

        lsu = findViewById(R.id.lichsu);
        dia = findViewById(R.id.dia);
        gdcd = findViewById(R.id.gdcd);
        anhvan = findViewById(R.id.anhvan);
    }


}