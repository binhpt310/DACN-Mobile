package com.example.dacn.Lich_su_lam_bai.Fragment;

import android.content.Intent;
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
import com.example.dacn.Lich_su_lam_bai.History;
import com.example.dacn.R;
import com.example.dacn.hoanthanhbai.hoanthanhbaithi;

import java.util.ArrayList;

public class EngFragment extends Fragment {

    private ArrayList<History> newsArrayList;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eng, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.result);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        HistoryAdapter historyAdapter = new HistoryAdapter(newsArrayList, new IClickItemHistory() {
            @Override
            public void onClickItemHistory(History history) {
                onClickGoToDeTail(history);
            }
        });
        recyclerView.setAdapter(historyAdapter);

    }

    private void onClickGoToDeTail(History history){
        Intent intent = new Intent(getActivity(), hoanthanhbaithi.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_history", history);
        intent.putExtras(bundle);
        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
        startActivity(intent);
    }
}

