package com.example.dacn.cauhoi;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.R;

import java.util.ArrayList;

public class search_question extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchquestion);
        RecyclerView recyclerView = findViewById(R.id.searchList);
        TextView nullText = findViewById(R.id.search_nulltext);

        ///// Can be modified by call api to get list data
        ArrayList<CardModelDataSearch> searchModelArrayList = new ArrayList<CardModelDataSearch>();
        searchModelArrayList.add(new CardModelDataSearch("Who is Nguyen", "She's a Stupid Cat"));
        ////

        // we are initializing our adapter class and passing our arraylist to it.
        SearchAdapter courseAdapter = new SearchAdapter(this, searchModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(courseAdapter);

        if(searchModelArrayList.size() == 0){
            nullText.setVisibility(View.VISIBLE);
            nullText.setText("Khong co ket qua");
            recyclerView.setVisibility(View.GONE);
        }
    }
}

