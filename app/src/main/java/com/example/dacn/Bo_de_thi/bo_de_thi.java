package com.example.dacn.Bo_de_thi;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.dangnhap.dang_ky;
import com.example.dacn.trangchu2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class bo_de_thi extends AppCompatActivity {

    private List<BoDe> Bodethimodels = new ArrayList<>();

    String tenmon,loaide;
    ImageView btn_back;
    RadioGroup radioGroup;
    RecyclerView recyclerView;
    TextView txt_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_de_thi);

        recyclerView = findViewById(R.id.recyclerview_bo_de_thi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tenmon = TruyenDuLieu.trMon;

        txt_toolbar = findViewById(R.id.text_toolbar_bode);
        txt_toolbar.setText(TruyenDuLieu.trTenMon);

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
                TruyenDuLieu.trMaDe = tenmon+loaide;

                HashMap<String, String> map = new HashMap<>();
                map.put("sub", TruyenDuLieu.trMaDe);

                Call<List<BoDe>> call = retrofitInterface.getBoDe(map);
                call.enqueue(new Callback<List<BoDe>>() {
                    @Override
                    public void onResponse(Call<List<BoDe>> call, Response<List<BoDe>> response) {
                        Bodethimodels = response.body();
                        if (response.code() == 200) {
                            Bo_de_thi_adapter bo_de_thi_adapter = new Bo_de_thi_adapter(bo_de_thi.this,Bodethimodels);
                            recyclerView.setAdapter(bo_de_thi_adapter);
                        } else if (response.code() == 404) {
                            Toast.makeText(bo_de_thi.this, "Lỗi", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<BoDe>> call, Throwable t) {
                        Toast.makeText(bo_de_thi.this,"Fail",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

}