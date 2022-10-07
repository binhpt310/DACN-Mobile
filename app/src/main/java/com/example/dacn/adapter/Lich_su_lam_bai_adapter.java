package com.example.dacn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.R;
import com.example.dacn.model.Lich_su_lam_bai_model;

import java.util.ArrayList;


public class Lich_su_lam_bai_adapter extends RecyclerView.Adapter<Lich_su_lam_bai_adapter.MyViewHolder> {
    ArrayList<Lich_su_lam_bai_model> LichSuLamBaiModels;
    Context context;
    public Lich_su_lam_bai_adapter(Context context, ArrayList<Lich_su_lam_bai_model> LichSuLamBaiModels){
        this.context = context;
        this.LichSuLamBaiModels = LichSuLamBaiModels;
    }

    @NonNull
    @Override
    public Lich_su_lam_bai_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_lich_su_lam_bai, parent, false);

        return new Lich_su_lam_bai_adapter.MyViewHolder(view);
    }


    @Override
    public int getItemCount() {

        return LichSuLamBaiModels.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tenmonhoc, tendethi;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            tenmonhoc = itemView.findViewById(R.id.txt_view_ten_mon_hoc);
            tendethi = itemView.findViewById(R.id.txt_view_ten_de_thi);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull Lich_su_lam_bai_adapter.MyViewHolder holder, int position) {
        holder.tenmonhoc.setText(LichSuLamBaiModels.get(position).getTen_mon_hoc());
        holder.tendethi.setText(LichSuLamBaiModels.get(position).getTen_de_thi());
    }
}
