package com.example.dacn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dacn.adapter.Bo_de_thi_adapter;
import com.example.dacn.model.Bo_de_thi_model;

import java.util.ArrayList;

public class bo_de_thi extends AppCompatActivity {
    ArrayList<Bo_de_thi_model> Bodethimodels = new ArrayList<Bo_de_thi_model>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_de_thi);
        setup_Bo_de_thi_model();
        RecyclerView recyclerView = findViewById(R.id.recyclerview_bo_de_thi);
        Bo_de_thi_adapter adapter = new Bo_de_thi_adapter(this, Bodethimodels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setup_Bo_de_thi_model(){
        Bodethimodels.add(new Bo_de_thi_model("Bộ đề thi số 1", "Đề thi THPTQG môn Địa năm 2018"));
        Bodethimodels.add(new Bo_de_thi_model("Bộ đề thi số 2", "Đề thi THPTQG môn Anh năm 2018"));
        Bodethimodels.add(new Bo_de_thi_model("Bộ đề thi số 2", "Đề thi THPTQG môn Toán năm 2018"));
    }
}