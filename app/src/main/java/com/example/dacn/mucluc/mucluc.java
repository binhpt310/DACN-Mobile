package com.example.dacn.mucluc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.Lich_su_lam_bai.lich_su_lam_bai;
import com.example.dacn.mucluc.Notification.NotificationsActivity;
import com.example.dacn.mucluc.ThayDoiThongTin.thaydoithongtin;
import com.example.dacn.popup.popup_dang_xuat;
import com.example.dacn.trangchu2;

import de.hdodenhof.circleimageview.CircleImageView;

public class mucluc extends AppCompatActivity {

    ConstraintLayout chinhsuathongtin, ketquahoctap, veapp, dangxuat, trogiup, hengiohoc;
    ImageView img_close;
    CircleImageView avamuluc;
    TextView txt_tenngdung, txt_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mucluc);

        khaibao();

        Glide.with(mucluc.this).load(TruyenDuLieu.tr_linkanh).into(avamuluc);

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

        trogiup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mucluc.this, trogiup.class);
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
                Intent intent = new Intent(getApplicationContext(), popup_dang_xuat.class);
                startActivity(intent);
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

        hengiohoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mucluc.this, NotificationsActivity.class);
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
        trogiup = findViewById(R.id.trogiup);
        hengiohoc = findViewById(R.id.hengiohoc);

        avamuluc = findViewById(R.id.ava_mucluc);
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

    @Override
    protected void onResume() {
        super.onResume();
        txt_tenngdung.setText(TruyenDuLieu.trTenTk_dnhap);
    }

    @Override
    public void onStart(){
        super.onStart();
        }
    }
