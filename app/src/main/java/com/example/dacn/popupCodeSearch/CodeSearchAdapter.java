package com.example.dacn.popupCodeSearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.R;
import com.example.dacn.search.CardModelDataSearch;

import java.util.ArrayList;

;

public class CodeSearchAdapter extends RecyclerView.Adapter<CodeSearchAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<CardModelDataSearch> searchModelArrayList;

    // Constructor
    public CodeSearchAdapter(Context context, ArrayList<CardModelDataSearch> courseModelArrayList) {
        this.context = context;
        this.searchModelArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public CodeSearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_datasearch, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CodeSearchAdapter.MyViewHolder holder, int position) {
        CardModelDataSearch model = searchModelArrayList.get(position);
        holder.question.setText(model.getQuestion());
        holder.answer.setText("Đáp án: " + model.getAnw());
    }

    @Override
    public int getItemCount() {
        return searchModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView question;
        private final TextView answer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question_search);
            answer = itemView.findViewById(R.id.ans_search);
        }
    }
}