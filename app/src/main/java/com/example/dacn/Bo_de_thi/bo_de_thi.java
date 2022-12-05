package com.example.dacn.Bo_de_thi;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.search.search_question;
import com.example.dacn.mucluc.trogiup;
import com.example.dacn.trangchu2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class bo_de_thi extends AppCompatActivity {

    private List<BoDe> Bodethimodels = new ArrayList<>();
    private List<BoDe> Bodeonmodels = new ArrayList<>();

    String tenmon,loaide;
    ImageView btn_back;
    RadioGroup radioGroup;
    RadioButton radioon, radiothi;
    RecyclerView recyclerView;
    TextView txt_toolbar;
    Button floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_de_thi);

        khaibao();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tenmon = TruyenDuLieu.trMon;
        txt_toolbar.setText(TruyenDuLieu.trTenMon);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bo_de_thi.this, trangchu2.class);
                startActivity(intent);
            }
        });

        if(radioon.isChecked())
        {
            loaide = "_review";
            TruyenDuLieu.trMaDe = tenmon+loaide;
            Log.e("made", TruyenDuLieu.trMaDe);

            HashMap<String, String> maps = new HashMap<>();
            maps.put("sub", TruyenDuLieu.trMaDe);

            Call<List<BoDe>> calls = retrofitInterface.getBoDe(maps);
            calls.enqueue(new Callback<List<BoDe>>() {
                @Override
                public void onResponse(Call<List<BoDe>> call, Response<List<BoDe>> response) {
                    Bodeonmodels = response.body();
                    if (response.code() == 200) {
                        Bo_de_on_adapter bo_de_on_adapter = new Bo_de_on_adapter(bo_de_thi.this,Bodeonmodels);
                        recyclerView.setAdapter(bo_de_on_adapter);
                    } else if (response.code() == 404) {
                        Toast.makeText(bo_de_thi.this, "Lỗi", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<List<BoDe>> call, Throwable t) {
                    Toast.makeText(bo_de_thi.this,"Fail",Toast.LENGTH_SHORT).show();
                }
            });
            View search_question;
            trogiup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(bo_de_thi.this, search_question.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                    finish();
                }
            });

        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_thi:
                        loaide = "_exam";
                        TruyenDuLieu.trMaDe = tenmon + loaide;
                        HashMap<String, String> map = new HashMap<>();
                        map.put("sub", TruyenDuLieu.trMaDe);
                        Call<List<BoDe>> call = retrofitInterface.getBoDe(map);
                        call.enqueue(new Callback<List<BoDe>>() {
                            @Override
                            public void onResponse(Call<List<BoDe>> call, Response<List<BoDe>> response) {
                                Bodethimodels = response.body();
                                if (response.code() == 200) {
                                    Bo_de_thi_adapter bo_de_thi_adapter = new Bo_de_thi_adapter(bo_de_thi.this, Bodethimodels);
                                    recyclerView.setAdapter(bo_de_thi_adapter);
                                } else if (response.code() == 404) {
                                    Toast.makeText(bo_de_thi.this, "Lỗi", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<BoDe>> call, Throwable t) {
                                Toast.makeText(bo_de_thi.this, "Fail", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case R.id.radio_on:
                        loaide = "_review";
                        TruyenDuLieu.trMaDe = tenmon + loaide;
                        HashMap<String, String> maps = new HashMap<>();
                        maps.put("sub", TruyenDuLieu.trMaDe);
                        Call<List<BoDe>> calls = retrofitInterface.getBoDe(maps);
                        calls.enqueue(new Callback<List<BoDe>>() {
                            @Override
                            public void onResponse(Call<List<BoDe>> call, Response<List<BoDe>> response) {
                                Bodeonmodels = response.body();
                                if (response.code() == 200) {
                                    Bo_de_on_adapter bo_de_on_adapter = new Bo_de_on_adapter(bo_de_thi.this, Bodeonmodels);
                                    recyclerView.setAdapter(bo_de_on_adapter);
                                } else if (response.code() == 404) {
                                    Toast.makeText(bo_de_thi.this, "Lỗi", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<List<BoDe>> call, Throwable t) {
                                Toast.makeText(bo_de_thi.this, "Fail", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                }
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bo_de_thi.this, search_question.class);
                startActivity(intent);
            }
        });
    }

    private void khaibao() {
        recyclerView = findViewById(R.id.recyclerview_bo_de_thi);
        txt_toolbar = findViewById(R.id.text_toolbar_bode);
        btn_back = findViewById(R.id.img_back_bo_de_thi);
        radioGroup = findViewById(R.id.radio_gr);
        radioon = findViewById(R.id.radio_on);
        radiothi = findViewById(R.id.radio_thi);
        floatingActionButton = findViewById(R.id.floatingActionButton);
    }

}