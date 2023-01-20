package com.example.dacn.search;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.R;

import java.util.ArrayList;

public class search_question extends AppCompatActivity {

    private ArrayList<CardModelDataSearch> searchModelArrayList = new ArrayList<CardModelDataSearch>();
    String tenmon, keyword;
    ImageButton btn_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchquestion);

        loadListData();

        btn_search = findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

