package com.example.dacn.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.R;


import java.util.List;

public class DataSearchAdapter extends RecyclerView.Adapter<DataSearchAdapter.MyViewHolder> {

    private final Context context;
    private final List<DataSearch> searchModelArrayList;
    private ClickCardReview clickCardReview;
    private ClickCardExam clickCardExam;

    public DataSearchAdapter(Context context, List<DataSearch> searchModelArrayList, ClickCardReview clickCardReview, ClickCardExam clickCardExam) {
        this.context = context;
        this.searchModelArrayList = searchModelArrayList;
        this.clickCardReview = clickCardReview;
        this.clickCardExam = clickCardExam;
    }

    public interface ClickCardReview {
        void onClickCardReview (List<String> listReview);
    }

    public interface ClickCardExam {
        void onClickCardExam (List<String> listExam);
    }

    @NonNull
    @Override
    public DataSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_datasearch, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataSearchAdapter.MyViewHolder holder, int position) {
        DataSearch model = searchModelArrayList.get(position);
        if (model == null) {
            return;
        }
        holder.question.setText(model.getQuestion());
        holder.answer.setText(model.getAnw());
        if (model.getReview().equals("")) holder.deon.setVisibility(View.GONE);
        if (model.getExam().equals("")) holder.dethi.setVisibility(View.GONE);

        holder.deon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCardReview.onClickCardReview(model.getListreview());

            }
        });

        holder.dethi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCardExam.onClickCardExam(model.getListexam());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (searchModelArrayList != null) {
            return searchModelArrayList.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView question,answer,deon,dethi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question_search);
            answer = itemView.findViewById(R.id.ans_search);
            deon = itemView.findViewById(R.id.btn_de_on);
            dethi = itemView.findViewById(R.id.btn_de_thi);
        }
    }
}