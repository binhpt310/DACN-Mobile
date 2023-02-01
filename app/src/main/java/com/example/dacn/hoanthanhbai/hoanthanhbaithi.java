package com.example.dacn.hoanthanhbai;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.Bo_de_thi.Bo_de_on_adapter;
import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.Lich_su_lam_bai.lich_su_lam_bai;
import com.example.dacn.Lich_su_lam_bai.subjectHistory;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.cauhoi.CauHoiTracNghiem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class hoanthanhbaithi extends AppCompatActivity {
    private List<NdungCardModel> ndungCardModels = new ArrayList<>();
    RecyclerView rcv_hoanthanhbaithi;
    ImageView img_quaylai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoanthanhbaithi);

        img_quaylai = findViewById(R.id.img_back_hoanthanhbaithi);
        rcv_hoanthanhbaithi = findViewById(R.id.rcv_hoanthanhbaithi);
        rcv_hoanthanhbaithi.setLayoutManager(new LinearLayoutManager(this));

        HashMap<String, String> map = new HashMap<>();
        map.put("sub", "Geo_review");
        map.put("Code", "3");
        Call<List<NdungCardModel>> call = retrofitInterface.getNdung(map);
        call.enqueue(new Callback<List<NdungCardModel>>() {
            @Override
            public void onResponse(Call<List<NdungCardModel>> call, Response<List<NdungCardModel>> response) {
                ndungCardModels = response.body();
                if (response.code() == 200) {
                    HoanThanh_Adapter hoanThanh_adapter = new HoanThanh_Adapter(ndungCardModels,hoanthanhbaithi.this);
                    rcv_hoanthanhbaithi.setAdapter(hoanThanh_adapter);
                } else if (response.code() == 404) {
                    Toast.makeText(hoanthanhbaithi.this, "Lỗi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<NdungCardModel>> call, Throwable t) {
                Toast.makeText(hoanthanhbaithi.this,"Fail",Toast.LENGTH_SHORT).show();
            }
        });
        View img_back_hoanthanhbaithi = findViewById(R.id.img_back_hoanthanhbaithi);

        img_back_hoanthanhbaithi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(hoanthanhbaithi.this, subjectHistory.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
            }
        });
    }
}