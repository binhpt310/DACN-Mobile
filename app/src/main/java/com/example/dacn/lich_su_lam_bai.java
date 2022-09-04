package com.example.dacn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dacn.adapter.Lich_su_lam_bai_adapter;
import com.example.dacn.model.Lich_su_lam_bai_model;

import java.util.ArrayList;

public class lich_su_lam_bai extends AppCompatActivity {

    ArrayList<Lich_su_lam_bai_model> LichSuLamBaiModels = new ArrayList<Lich_su_lam_bai_model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_lam_bai);
        setup_lichsulambai_model();
        RecyclerView recyclerView = findViewById(R.id.recyclerview_lich_su_lam_bai);
        Lich_su_lam_bai_adapter adapter = new Lich_su_lam_bai_adapter(this,LichSuLamBaiModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setup_lichsulambai_model() {
        String[] tenmonhoc = getResources().getStringArray(R.array.ten_mon_hoc);
        String[] tendethi = getResources().getStringArray(R.array.ten_de_thi);

        while (tenmonhoc != null && tendethi != null)
        {
            int i = 0;
            LichSuLamBaiModels.add(new Lich_su_lam_bai_model(tenmonhoc[i], tendethi[i]));
            i++;
        }
    }
}