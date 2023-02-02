package com.example.dacn.Lich_su_lam_bai;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.Lich_su_lam_bai.Fragment.IClickItemHistory;
import com.example.dacn.R;
import com.example.dacn.mucluc.Notification.Notification;


import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
     private IClickItemHistory iClickItemHistory;
     private List<History> newsArrayList;

    public void setList (List<History> list) {
        this.newsArrayList = list;
        notifyDataSetChanged();
    }

     public HistoryAdapter(List<History> newsArrayList, IClickItemHistory listener) {
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

         History hPosition = newsArrayList.get(position);

         holder.txt_title_cardlsu.setText("Đề số " + hPosition.getCode());

         if (hPosition.getSocauchualam().equals("")) {
             holder.nullLayout.setVisibility(View.GONE);
         } else holder.txt_caukhong_cardlsu.setText(hPosition.getSocauchualam());

         holder.txt_causai_cardlsu.setText(hPosition.getSocausai());
         holder.txt_caudung_cardlsu.setText(hPosition.getSocaudung());

         if (hPosition.getTime().equals("")) {
             holder.txt_time_cardlsu.setVisibility(View.GONE);
         } holder.txt_time_cardlsu.setText(hPosition.getTime());

         holder.cardview.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                iClickItemHistory.onClickItemHistory(hPosition);
             }

         });
     }

     @Override
     public int getItemCount() {
         if (newsArrayList != null) {
             return newsArrayList.size();
         }
         return 0;
     }

     public static class MyViewHolder extends RecyclerView.ViewHolder{

         CardView cardview;
         TextView txt_title_cardlsu,txt_time_cardlsu,txt_caudung_cardlsu,txt_causai_cardlsu,txt_caukhong_cardlsu;
         LinearLayout nullLayout;

         public MyViewHolder(@NonNull View itemView) {

             super(itemView);
             txt_title_cardlsu = itemView.findViewById(R.id.txt_title_cardlsu);
             txt_time_cardlsu = itemView.findViewById(R.id.txt_time_cardlsu);
             txt_caukhong_cardlsu = itemView.findViewById(R.id.txt_caukhong_cardlsu);
             txt_caudung_cardlsu = itemView.findViewById(R.id.txt_caudung_cardlsu);
             txt_causai_cardlsu = itemView.findViewById(R.id.txt_causai_cardlsu);

             cardview = itemView.findViewById(R.id.layout_item);

             nullLayout = itemView.findViewById(R.id.nullLayout);
         }
     }

 }