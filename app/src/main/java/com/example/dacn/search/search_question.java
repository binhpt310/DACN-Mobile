package com.example.dacn.search;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.Bo_de_thi.Bo_de_on_adapter;
import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.dangnhap.dang_nhap;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class search_question extends AppCompatActivity {

    List<CardModelDataSearch> searchModelArrayList = new ArrayList<>();
    String tenmon, keyword;
    ImageView btn_search;
    ProgressDialog progressdialog;
    EditText nhaptu;
    RecyclerView recyclerView;
    TextView nullText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchquestion);

        khaibao();

        progressdialog = new ProgressDialog(search_question.this);
        progressdialog.setMessage("Loadinggg");

        recyclerView.setLayoutManager(new LinearLayoutManager(search_question.this, LinearLayoutManager.VERTICAL, false));

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressdialog.show();
                HashMap<String, String> maps = new HashMap<>();
                maps.put("search", nhaptu.getText().toString());
                maps.put("sub", TruyenDuLieu.trMon);
                Log.e("search", nhaptu.getText().toString());
                Log.e("sub", TruyenDuLieu.trMon);

                Call<List<CardModelDataSearch>> calls = retrofitInterface.getSearch(maps);
                calls.enqueue(new Callback<List<CardModelDataSearch>>() {
                    @Override
                    public void onResponse(Call<List<CardModelDataSearch>> call, Response<List<CardModelDataSearch>> response) {
                        progressdialog.dismiss();
                        if (response.code() == 200) {
                            Toast.makeText(search_question.this, "OK", Toast.LENGTH_SHORT).show();
                            searchModelArrayList = response.body();
                            SearchAdapter courseAdapter = new SearchAdapter(search_question.this, searchModelArrayList);
                            recyclerView.setAdapter(courseAdapter);
                        } else if (response.code() == 401) {
                            Toast.makeText(search_question.this, "Không tìm thấy từ khóa", Toast.LENGTH_SHORT).show();
                        } else if (response.code() == 404) {
                            Toast.makeText(search_question.this, "Lỗi", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<CardModelDataSearch>> call, Throwable t) {
                        progressdialog.dismiss();
                        nullText.setVisibility(View.VISIBLE);
                        nullText.setText("Không có kết quả");
                        Toast.makeText(search_question.this, t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void khaibao() {
        btn_search = findViewById(R.id.search_button);
        nhaptu = findViewById(R.id.txtsearch_key);
        recyclerView = findViewById(R.id.searchList);
        nullText = findViewById(R.id.search_nulltext);
    }

}

