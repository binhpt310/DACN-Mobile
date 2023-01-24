package com.example.dacn.search;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

public class search_question extends AppCompatActivity {

    private ArrayList<CardModelDataSearch> searchModelArrayList = new ArrayList<CardModelDataSearch>();
    String tenmon, keyword;
    ImageView btn_search;
    ProgressDialog progressdialog;
    TextView nhaptu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchquestion);

        loadListData();

        progressdialog = new ProgressDialog(search_question.this);
        progressdialog.setMessage("Loadinggg");

        btn_search = findViewById(R.id.search_button);
        nhaptu = findViewById(R.id.txtsearch_key);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressdialog.show();
                HashMap<String, String> maps = new HashMap<>();
                maps.put("search", nhaptu.toString());
                maps.put("sub", TruyenDuLieu.trMaDe);

                Call<List<CardModelDataSearch>> calls = retrofitInterface.getSearch(maps);
                // TODO: change list data for call api

                // Load data to search list again
                loadListData();
            }
        });
    }

    public void loadListData(){
        RecyclerView recyclerView = findViewById(R.id.searchList);
        TextView nullText = findViewById(R.id.search_nulltext);
        // we are initializing our adapter class and passing our arraylist to it.
        SearchAdapter courseAdapter = new SearchAdapter(this, searchModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(courseAdapter);

        if(searchModelArrayList.size() == 0) {
            nullText.setVisibility(View.VISIBLE);
            nullText.setText("Khong co ket qua");
            recyclerView.setVisibility(View.GONE);
        }
    }
}

