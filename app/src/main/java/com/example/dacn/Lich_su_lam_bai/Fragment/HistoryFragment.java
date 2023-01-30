package com.example.dacn.Lich_su_lam_bai.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dacn.Lich_su_lam_bai.HistoryAdapter;
import com.example.dacn.Lich_su_lam_bai.history;
import com.example.dacn.R;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private String[] newsHeading;
    private ArrayList<history> newsArrayList;
    private int[] caudung;
    private int[] causai;
    private RecyclerView recyclerView;

    public HistoryFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInitialize();
        recyclerView = view.findViewById(R.id.result);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        HistoryAdapter historyAdapter = new HistoryAdapter(getContext(),newsArrayList);
        recyclerView.setAdapter(historyAdapter);
        historyAdapter.notifyDataSetChanged();
        Log.d("Success", "recycle view");

    }

    private void dataInitialize() {

        newsArrayList = new ArrayList<history>();

        // Write a code to get data from api and change data in 3 arrays below
        newsHeading = new String[]{
                getString(R.string.heading_one),
                getString(R.string.heading_two),
                getString(R.string.heading_three),

        };
        caudung = new int[]{30,10,2};
        causai = new int[]{10,30,38};
        //

        for(int i = 0; i< newsHeading.length; i++){
            history h = new history(newsHeading[i],caudung[i], causai[i]);
            newsArrayList.add(h);
        }

    }
}
