package com.example.dacn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.R;
import com.example.dacn.model.Bo_de_thi_model;

import java.util.ArrayList;
import java.util.List;

public class Bo_de_thi_adapter extends RecyclerView.Adapter<Bo_de_thi_adapter.MyViewHolder> {
    ArrayList<Bo_de_thi_model> Bodethimodels;
    Context context;
    public Bo_de_thi_adapter(Context context, ArrayList<Bo_de_thi_model> Bodethimodels){
        this.context = context;
        this.Bodethimodels = Bodethimodels;
    }

    @NonNull
    @Override
    public Bo_de_thi_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_bo_de_thi, parent, false);

        return new Bo_de_thi_adapter.MyViewHolder(view);
    }


    @Override
    public int getItemCount() {

        return Bodethimodels.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        TextView sothutu, tendethi;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            sothutu = itemView.findViewById(R.id.so_thu_tu_bo_de_thi);
            tendethi = itemView.findViewById(R.id.ten_bo_de_thi);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull Bo_de_thi_adapter.MyViewHolder holder, int position) {
        holder.sothutu.setText(Bodethimodels.get(position).getSo_thu_tu_bo_de_thi());
        holder.tendethi.setText(Bodethimodels.get(position).getTen_bo_de_thi());
    }
}