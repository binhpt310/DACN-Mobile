package com.example.dacn.Lich_su_lam_bai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dacn.R;

public class lich_su_lam_bai extends AppCompatActivity {

    LinearLayout llFolderOn, llFolderThi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_lam_bai);

        llFolderOn = findViewById(R.id.layout_folder_on);
        llFolderThi = findViewById(R.id.layout_folder_thi);

        llFolderOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lich_su_lam_bai.this, subjectHistory.class);
                startActivity(intent);
            }
        });

        llFolderThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lich_su_lam_bai.this, subjectHistory.class);
                startActivity(intent);
            }
        });
    }

}