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

    private ArrayList<CardModelDataSearch> searchModelArrayList = new ArrayList<CardModelDataSearch>();
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
        loadListData();

        progressdialog = new ProgressDialog(search_question.this);
        progressdialog.setMessage("Loadinggg");

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressdialog.show();
                // TODO: change list data for call api
                HashMap<String, String> maps = new HashMap<>();
                maps.put("search", nhaptu.getText().toString());
                maps.put("sub", TruyenDuLieu.trMaDe);
                Log.e("search", nhaptu.getText().toString());
                Log.e("sub", TruyenDuLieu.trMaDe);

                Call<List<CardModelDataSearch>> calls = retrofitInterface.getSearch(maps);
                calls.enqueue(new Callback<List<CardModelDataSearch>>() {
                    @Override
                    public void onResponse(Call<List<CardModelDataSearch>> call, Response<List<CardModelDataSearch>> response) {
                        progressdialog.dismiss();
                        Toast.makeText(search_question.this, "OK", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<List<CardModelDataSearch>> call, Throwable t) {
                        progressdialog.dismiss();
                        nullText.setVisibility(View.VISIBLE);
                        nullText.setText("Khong co ket qua");
                        recyclerView.setVisibility(View.GONE);
                        Toast.makeText(search_question.this, t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
                // Load data to search list again
                loadListData();
            }
        });
    }

    private void khaibao() {
        btn_search = findViewById(R.id.search_button);
        nhaptu = findViewById(R.id.txtsearch_key);
        recyclerView = findViewById(R.id.searchList);
        nullText = findViewById(R.id.search_nulltext);
    }

    public void loadListData(){
        // we are initializing our adapter class and passing our arraylist to it.
        SearchAdapter courseAdapter = new SearchAdapter(this, searchModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(courseAdapter);
    }
}

