package com.example.dacn.Bo_de_thi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.trangchu2;

import java.util.ArrayList;

public class bo_de_thi extends AppCompatActivity {

    ArrayList<Bo_de_thi_model> Bodethimodels = new ArrayList<Bo_de_thi_model>();

    String tenmon,loaide,made;
    ImageView btn_back;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_de_thi);

        setup_Bo_de_thi_model();
        RecyclerView recyclerView = findViewById(R.id.recyclerview_bo_de_thi);
        Bo_de_thi_adapter adapter = new Bo_de_thi_adapter(this, Bodethimodels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tenmon = TruyenDuLieu.trMon;

        btn_back = findViewById(R.id.img_back_bo_de_thi);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bo_de_thi.this, trangchu2.class);
                startActivity(intent);
            }
        });

        radioGroup = findViewById(R.id.radio_gr);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_thi:
                        loaide = "_review";
                        break;
                    case R.id.radio_on:
                        loaide = "_exam";
                        break;
                }
                made = tenmon+loaide;
                Log.e("vv", made);
            }
        });

    }

    private void setup_Bo_de_thi_model(){
        Bodethimodels.add(new Bo_de_thi_model("Bộ đề thi số 1", R.drawable.img_history));
        Bodethimodels.add(new Bo_de_thi_model("Bộ đề thi số 2", R.drawable.img_history));
        Bodethimodels.add(new Bo_de_thi_model("Bộ đề thi số 2", R.drawable.img_history));
    }
}