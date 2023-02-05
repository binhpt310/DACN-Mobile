package com.example.dacn.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.Lich_su_lam_bai.lich_su_lam_bai;
import com.example.dacn.R;
import com.example.dacn.popup.popup_search;

import java.util.List;

public class ListCodeAdapter extends RecyclerView.Adapter<ListCodeAdapter.MyViewHolder> {

    private static Context context;
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
    }

    @Override
    public int getItemCount() {
        return boDeArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtCode;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCode = itemView.findViewById(R.id.tvCode);
            txtCode.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(context, lich_su_lam_bai.class);
                    context.startActivity(intent);;
                }
            });
        }
    }
}