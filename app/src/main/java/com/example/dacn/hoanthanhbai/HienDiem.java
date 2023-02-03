package com.example.dacn.hoanthanhbai;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.Bo_de_thi.Bo_de_on_adapter;
import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HienDiem extends AppCompatActivity {
    ImageView back;
    TextView tvDiem,tvXem;
    RecyclerView rcv;
    private List<BoDe> Bodeonmodels = new ArrayList<>();

    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_diem);

        khaibao();

        progressdialog = new ProgressDialog(HienDiem.this);
        progressdialog.setMessage("Loadinggg");

        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        callApi();
    }

    private void khaibao() {
        back = findViewById(R.id.img_back_hiendiem);
        tvDiem = findViewById(R.id.tv_diem_hiendiem);
        tvXem = findViewById(R.id.tv_xemlai_hiendiem);
        rcv = findViewById(R.id.rcv_hiendiem);
    }

    private void callApi() {
        progressdialog.show();
        HashMap<String, String> maps = new HashMap<>();
        maps.put("sub", TruyenDuLieu.trMaDe);

        Call<List<BoDe>> calls = retrofitInterface.getBoDe(maps);
        calls.enqueue(new Callback<List<BoDe>>() {
            @Override
            public void onResponse(Call<List<BoDe>> call, Response<List<BoDe>> response) {
                progressdialog.dismiss();
                if (response.code() == 200) {
                    Bodeonmodels = response.body();
                    Bo_de_on_adapter bo_de_on_adapter = new Bo_de_on_adapter(HienDiem.this,Bodeonmodels);
                    rcv.setAdapter(bo_de_on_adapter);
                    bo_de_on_adapter.notifyDataSetChanged();
                } else if (response.code() == 404) {
                    Toast.makeText(HienDiem.this, "Lỗi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<BoDe>> call, Throwable t) {
                Toast.makeText(HienDiem.this,"Fail",Toast.LENGTH_SHORT).show();
            }
        });
    }
}