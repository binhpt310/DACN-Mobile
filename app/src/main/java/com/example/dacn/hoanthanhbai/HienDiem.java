package com.example.dacn.hoanthanhbai;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.Bo_de_thi.Bo_de_on_adapter;
import com.example.dacn.Bo_de_thi.Bo_de_thi_adapter;
import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.cauhoi.ontap_tracnghiem;
import com.example.dacn.dangnhap.dang_nhap;
import com.example.dacn.trangchu2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HienDiem extends AppCompatActivity {
    ImageView back;
    TextView tvDiem,tvXem,tvCau;
    RecyclerView rcv;
    private List<BoDe> Bodethimodels = new ArrayList<>();
    private List<BoDe> Bodeonmodels = new ArrayList<>();

    ProgressDialog progressdialog;

    String tenmon,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_diem);

        khaibao();

        progressdialog = new ProgressDialog(HienDiem.this);
        progressdialog.setMessage("Loadinggg");

        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        tvDiem.setText(TruyenDuLieu.trDiem);
        tvCau.setText(TruyenDuLieu.trCau);

        callApi();

        if (TruyenDuLieu.trMon.equals("His")) tenmon = "History";
        else if (TruyenDuLieu.trMon.equals("Eng")) tenmon = "English";
        else if (TruyenDuLieu.trMon.equals("Geo")) tenmon = "Geography";
        else if (TruyenDuLieu.trMon.equals("Gdcd")) tenmon = "Gdcd";
        url = "https://newdacn.onrender.com/getresult?email="+ TruyenDuLieu.trEmail_dnhap +"&type="+TruyenDuLieu.trDangBai+"&sub="+tenmon;

        tvXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HienDiem.this, hoanthanhbaithi.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_history_url", url);
                Log.e("url",url);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HienDiem.this, trangchu2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
            }
        });
    }

    private void khaibao() {
        back = findViewById(R.id.img_back_hiendiem);

        tvDiem = findViewById(R.id.tv_diem_hiendiem);
        tvXem = findViewById(R.id.tv_xemlai_hiendiem);
        tvCau = findViewById(R.id.tv_cau_hiendiem);

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
                    if (TruyenDuLieu.trMaDe.equals("His_review") || TruyenDuLieu.trMaDe.equals("Geo_review") || TruyenDuLieu.trMaDe.equals("Eng_review") || TruyenDuLieu.trMaDe.equals("Gdcd_review")) {
                        Bo_de_on_adapter bo_de_on_adapter = new Bo_de_on_adapter(HienDiem.this,Bodeonmodels);
                        rcv.setAdapter(bo_de_on_adapter);
                        bo_de_on_adapter.notifyDataSetChanged();
                    } else if (TruyenDuLieu.trMaDe.equals("His_exam") || TruyenDuLieu.trMaDe.equals("Geo_exam") || TruyenDuLieu.trMaDe.equals("Eng_exam") || TruyenDuLieu.trMaDe.equals("Gdcd_exam")) {
                        Bo_de_thi_adapter bo_de_thi_adapter = new Bo_de_thi_adapter(HienDiem.this, Bodethimodels);
                        rcv.setAdapter(bo_de_thi_adapter);
                        bo_de_thi_adapter.notifyDataSetChanged();
                    }

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