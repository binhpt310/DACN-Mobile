package com.example.dacn.Lich_su_lam_bai;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.Lich_su_lam_bai.Fragment.IClickItemHistory;
import com.example.dacn.R;


import java.util.ArrayList;

 public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
     private IClickItemHistory iClickItemHistory;
     ArrayList<History> newsArrayList;
     public HistoryAdapter( ArrayList<History> newsArrayList, IClickItemHistory listener) {
         this.iClickItemHistory = listener;
         this.newsArrayList = newsArrayList;
     }

     @NonNull
     @Override
     public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

         View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lich_su_lam_bai,parent,false);
         return new MyViewHolder(v);
     }

     @Override
     public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

         History history = newsArrayList.get(position);
         holder.txt_title_cardlsu.setText(history.getHeading());
         holder.txt_caudung_cardlsu.setText(String.valueOf(history.getTrue()));
         holder.txt_causai_cardlsu.setText(String.valueOf(history.getFalse()));

         holder.trueLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                iClickItemHistory.onClickItemHistory(history);
             }

         });
         holder.falseLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 iClickItemHistory.onClickItemHistory(history);
             }
         });
     }

     @Override
     public int getItemCount() {
         return newsArrayList.size();
     }

     public static class MyViewHolder extends RecyclerView.ViewHolder{

         LinearLayout trueLayout;
         LinearLayout falseLayout;
         TextView txt_title_cardlsu;
         TextView txt_caudung_cardlsu,txt_causai_cardlsu;

         public MyViewHolder(@NonNull View itemView) {

             super(itemView);
             txt_title_cardlsu = itemView.findViewById(R.id.txt_title_cardlsu);
             txt_caudung_cardlsu = itemView.findViewById(R.id.txt_caudung_cardlsu);
             txt_causai_cardlsu = itemView.findViewById(R.id.txt_causai_cardlsu);
             trueLayout = itemView.findViewById(R.id.trueLayout);
             falseLayout = itemView.findViewById(R.id.falseLayout);
         }
     }

 }