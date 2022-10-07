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
import com.example.dacn.Lich_su_lam_bai.lich_su_lam_bai;
import com.example.dacn.trangchu2;

import de.hdodenhof.circleimageview.CircleImageView;

public class mucluc extends AppCompatActivity {

    ConstraintLayout chinhsuathongtin, ketquahoctap, veapp, dangxuat;
    ImageView img_close;
    CircleImageView avamuluc;
    TextView txt_tenngdung, txt_email;
    String tenngdung,email;
    Intent intent = getIntent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mucluc);

        khaibao();

        tenngdung = TruyenDuLieu.trTenTk_dnhap;
        email = TruyenDuLieu.trEmail_dnhap;

        txt_email.setText(email);
        txt_tenngdung.setText(tenngdung);

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

                TruyenDuLieu.trEmail_dnhap="";
                TruyenDuLieu.trTenTk_dnhap="";
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

    /*private void nextActivity(){
        Intent intent = new Intent(mucluc.this, thaydoithongtin.class);
        intent.putExtra("key_tenngdung", tenngdung);
        startActivityForResult(intent, MY_REQUEST_CODE);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (MY_REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK) {
            txt_tenngdung.setText(data.getStringExtra("key_key_tenngdung"));
        }
    }*/
