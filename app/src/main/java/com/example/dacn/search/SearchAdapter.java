package com.example.dacn.search;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.R;
import com.example.dacn.popup.popup_search;
import com.example.dacn.popup.popup_tro_ve;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private static Context context;
    private final List<CardModelDataSearch> searchModelArrayList;

    // Constructor
    public SearchAdapter(Context context, List<CardModelDataSearch> courseModelArrayList) {
        this.context = context;
        this.searchModelArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_datasearch, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {
        CardModelDataSearch model = searchModelArrayList.get(position);
        holder.question.setText(model.getQuestion());
        holder.answer.setText(model.getAnw());
    }

    @Override
    public int getItemCount() {
        return searchModelArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView question;
        private final TextView answer;
        private final TextView de_on;
        private final TextView de_thi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question_search);
            answer = itemView.findViewById(R.id.ans_search);
            de_on = itemView.findViewById(R.id.btn_de_on);
            de_thi = itemView.findViewById(R.id.btn_de_thi);
            de_on.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(context, popup_search.class);
                    context.startActivity(intent);;

                }
            });
            de_on.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(context, popup_tro_ve.class);
                    context.startActivity(intent);;

                }
            });
        }
    }
}