package com.example.dacn.Bo_de_thi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.cauhoi.ontap_tracnghiem;
import com.example.dacn.cauhoi.thi_tracnghiem;

import java.util.ArrayList;
import java.util.List;

public class Bo_de_thi_adapter extends RecyclerView.Adapter<Bo_de_thi_adapter.MyViewHolder> {
    private final List<BoDe> Bodethimodels;
    private Context mcontext;

    public Bo_de_thi_adapter(Context context, List<BoDe> Bodethimodels){
        this.mcontext = context;
        this.Bodethimodels = Bodethimodels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_bo_de_thi, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public int getItemCount() {
        if (Bodethimodels != null) {
            return Bodethimodels.size();
        }
        return 0;
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView sothutu,textView;
        private ImageView img_mon;
        private CardView cardView;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            sothutu = itemView.findViewById(R.id.so_thu_tu_bo_de_thi);
            img_mon = itemView.findViewById(R.id.img_mon_thi);
            textView = itemView.findViewById(R.id.textview_thi);
            cardView = itemView.findViewById(R.id.layout_card_dethi);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull Bo_de_thi_adapter.MyViewHolder holder, int position) {
        BoDe boDe = Bodethimodels.get(position);
        if (boDe == null) {
            return;
        }
        holder.sothutu.setText(String.valueOf(boDe.getCode()));
        if (TruyenDuLieu.trMon == "His") {
            holder.img_mon.setImageResource(R.drawable.img_history);
        } else if (TruyenDuLieu.trMon == "Geo") {
            holder.img_mon.setImageResource(R.drawable.img_global);
        } else if (TruyenDuLieu.trMon == "Eng") {
            holder.img_mon.setImageResource(R.drawable.img_international);
        } else if (TruyenDuLieu.trMon == "Gdcd") {
            holder.img_mon.setImageResource(R.drawable.img_emotional);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity(boDe);
            }
        });
    }

    private void nextActivity(BoDe boDe) {
        Intent intent = new Intent(mcontext, thi_tracnghiem.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Truyền mã bộ đề", boDe);
        intent.putExtras(bundle);
        mcontext.startActivity(intent);
    }
}