package com.example.dacn.Lich_su_lam_bai;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.R;


import java.util.ArrayList;

 public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
     Context context;
     ArrayList<history> newsArrayList;
     public HistoryAdapter(Context context, ArrayList<history> newsArrayList) {

         this.context = context;
         this.newsArrayList = newsArrayList;
     }

     @NonNull
     @Override
     public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

         View v = LayoutInflater.from(context).inflate(R.layout.card_lich_su_lam_bai,parent,false);
         return new MyViewHolder(v);
     }

     @Override
     public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

         history news = newsArrayList.get(position);
         holder.txt_title_cardlsu.setText(news.heading);
         holder.txt_caudung_cardlsu.setText(news.Rtrue);
     }

     @Override
     public int getItemCount() {
         return newsArrayList.size();
     }

     public static class MyViewHolder extends RecyclerView.ViewHolder{

         TextView txt_title_cardlsu;
         TextView txt_caudung_cardlsu,txt_causai_cardlsu;

         public MyViewHolder(@NonNull View itemView) {

             super(itemView);
             txt_title_cardlsu = itemView.findViewById(R.id.txt_title_cardlsu);
             txt_caudung_cardlsu = itemView.findViewById(R.id.txt_caudung_cardlsu);
             txt_causai_cardlsu = itemView.findViewById(R.id.txt_causai_cardlsu);

         }
     }

 }