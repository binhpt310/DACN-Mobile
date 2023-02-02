package com.example.dacn.Lich_su_lam_bai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.mucluc.mucluc;

public class lich_su_lam_bai extends AppCompatActivity {

    LinearLayout llFolderOn, llFolderThi;
    ConstraintLayout toolbar_lichsulambai;

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
                TruyenDuLieu.trDangBai = "review";
                startActivity(intent);
            }
        });

        llFolderThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lich_su_lam_bai.this, subjectHistory.class);
                TruyenDuLieu.trDangBai = "exam";
                startActivity(intent);
            }
        });

        toolbar_lichsulambai = findViewById(R.id.toolbar_lichsulambai);
        toolbar_lichsulambai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lich_su_lam_bai.this, mucluc.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
            }
        });
    }

}
