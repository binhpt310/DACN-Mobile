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

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private final Context context;
    private final List<CardModelDataSearch> searchModelArrayList;

    // Constructor
    public SearchAdapter(Context context, List<CardModelDataSearch> courseModelArrayList) {
        this.context = context;
        this.searchModelArrayList = courseModelArrayList;
    }

/*
    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        Intent intent = new Intent(search_question.this, popup_code.class);

//        View popupView = inflater.inflate(R.layout.activity_popup_search, null);


        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
*/
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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question_search);
            answer = itemView.findViewById(R.id.ans_search);
        }
    }
}