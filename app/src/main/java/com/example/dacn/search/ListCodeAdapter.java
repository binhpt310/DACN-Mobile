package com.example.dacn.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.Lich_su_lam_bai.lich_su_lam_bai;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.cauhoi.ontap_tracnghiem;
import com.example.dacn.cauhoi.thi_tracnghiem;

import java.util.List;

public class ListCodeAdapter extends RecyclerView.Adapter<ListCodeAdapter.MyViewHolder> {

    private Context context;
    private final List<BoDe> boDeArrayList;

    // Constructor
    public ListCodeAdapter(Context context, List<BoDe> boDeArrayList) {
        this.context = context;
        this.boDeArrayList = boDeArrayList;
    }

    @NonNull
    @Override
    public ListCodeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_searchcode, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCodeAdapter.MyViewHolder holder, int position) {
        BoDe bode = boDeArrayList.get(position);
        holder.txtCode.setText(bode.getCode());
        holder.ll_cardsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TruyenDuLieu.trMaDe.equals("His_review") || TruyenDuLieu.trMaDe.equals("Geo_review") || TruyenDuLieu.trMaDe.equals("Eng_review") || TruyenDuLieu.trMaDe.equals("Gdcd_review")) {
                    nextActivityOn(bode);
                } else if (TruyenDuLieu.trMaDe.equals("His_exam") || TruyenDuLieu.trMaDe.equals("Geo_exam") || TruyenDuLieu.trMaDe.equals("Eng_exam") || TruyenDuLieu.trMaDe.equals("Gdcd_exam")) {
                    nextActivityThi(bode);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (boDeArrayList != null) {
            return boDeArrayList.size();
        }
        return 0;
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCode;
        private LinearLayout ll_cardsearch;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCode = itemView.findViewById(R.id.tvCode);
            ll_cardsearch = itemView.findViewById(R.id.ll_cardsearch);
        }
    }

    private void nextActivityOn(BoDe boDe) {
        Intent intent = new Intent(context, ontap_tracnghiem.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Truyền mã bộ đề", boDe);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private void nextActivityThi(BoDe boDe) {
        Intent intent = new Intent(context, thi_tracnghiem.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Truyền mã bộ đề", boDe);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}