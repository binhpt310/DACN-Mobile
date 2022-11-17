package com.example.dacn.hoanthanhbai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.R;

import java.util.List;

public class HoanThanh_Adapter extends RecyclerView.Adapter<HoanThanh_Adapter.HoanThanhViewHolder> {

    private List<NdungCardModel> ndungCardModels;
    private Context context;

    public HoanThanh_Adapter(List<NdungCardModel> ndungCardModels, Context context) {
        this.ndungCardModels = ndungCardModels;
        this.context = context;
    }

    @NonNull
    @Override
    public HoanThanhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cauhoi, parent, false);
        return new HoanThanhViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (ndungCardModels != null) {
            return ndungCardModels.size();
        }
        return 0;
    }

    public static class HoanThanhViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_cauhoi,tv_cauchon,tv_dapan;

        public HoanThanhViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_cauhoi = itemView.findViewById(R.id.txt_cauhoi_cardcauhoi);
            tv_cauchon = itemView.findViewById(R.id.txt_caudachon_cardcauhoi);
            tv_dapan = itemView.findViewById(R.id.txt_dapan_cardcauhoi);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull HoanThanhViewHolder holder, int position) {
        NdungCardModel ndungCardModel = ndungCardModels.get(position);
        if (ndungCardModel == null) {
            return;
        }
        holder.tv_cauhoi.setText(ndungCardModel.getQuestion());
        holder.tv_cauchon.setText("Câu đã chọn: "+ndungCardModel.getA());
        holder.tv_dapan.setText("Đáp án: "+ndungCardModel.getAnw());
    }

}
