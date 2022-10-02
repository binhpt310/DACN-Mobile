package com.example.dacn.mucluc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.dangnhap.dang_nhap;
import com.example.dacn.lich_su_lam_bai;
import com.example.dacn.trangchu2;

public class mucluc extends AppCompatActivity {

    ConstraintLayout chinhsuathongtin, ketquahoctap, veapp, dangxuat;
    ImageView img_close;
    TextView txt_tenngdung, txt_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mucluc);

        khaibao();

        txt_email.setText(TruyenDuLieu.trEmail_dnhap);
        txt_tenngdung.setText(TruyenDuLieu.trTenTk_dnhap);

        chinhsuathongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mucluc.this, thaydoithongtin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                finish();
            }
        });

        veapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mucluc.this, veapp.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                finish();
            }
        });

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mucluc.this, trangchu2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                finish();
            }
        });

        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mucluc.this, dang_nhap.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                finish();
            }
        });

        ketquahoctap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mucluc.this, lich_su_lam_bai.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                finish();
            }
        });
    }

    private void khaibao() {
        chinhsuathongtin = findViewById(R.id.chinhsuathongtin);
        ketquahoctap = findViewById(R.id.ketquahoctap);
        veapp = findViewById(R.id.veapp);
        dangxuat = findViewById(R.id.dangxuat);
        img_close = findViewById(R.id.img_tat);

        txt_email = findViewById(R.id.txt_email_mucluc);
        txt_tenngdung = findViewById(R.id.txt_tenngdung_mucluc);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        Intent intent = new Intent(this, trangchu2.class);
        startActivity(intent);
    }
}