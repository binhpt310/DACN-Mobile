package com.example.dacn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dacn.cauhoi.thi_tracnghiem;
import com.example.dacn.mucluc.mucluc;

public class trangchu2 extends AppCompatActivity {

    ImageView img_mucluc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu2);

        img_mucluc = findViewById(R.id.img_mucluc);
        //TextView thi = findViewById(R.id.test_txt_trang_chu);

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
                Intent intent = new Intent(getApplicationContext(), thi_tracnghiem.class);
                startActivity(intent);
            }
        });*/
    }
}