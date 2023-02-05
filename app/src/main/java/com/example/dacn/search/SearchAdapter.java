package com.example.dacn.search;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.R;
import com.example.dacn.dangnhap.dang_nhap;
import com.example.dacn.popup.popup_dang_xuat;
import com.example.dacn.popupCodeSearch.popup_code;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private final Context context;
    private final List<DataSearch> searchModelArrayList;

    // Constructor
    public SearchAdapter(Context context, List<DataSearch> courseModelArrayList) {
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
        DataSearch model = searchModelArrayList.get(position);
        if (model == null) {
            return;
        }
        holder.question.setText(model.getQuestion());
        holder.answer.setText(model.getAnw());
        if (model.getReview().equals("")) holder.deon.setVisibility(View.GONE);
        if (model.getExam().equals("")) holder.dethi.setVisibility(View.GONE);

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
                Intent intent = new Intent(context, popup_search.class);
                context.startActivity(intent);;

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

    // View holder class for initializing of your views such as TextView and Imageview
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView question,answer,deon,dethi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question_search);
            answer = itemView.findViewById(R.id.ans_search);
            deon = itemView.findViewById(R.id.listcode_search_1);
            dethi = itemView.findViewById(R.id.listcode_search_2);
        }
    }
}