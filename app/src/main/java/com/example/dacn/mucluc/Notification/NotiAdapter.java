package com.example.dacn.mucluc.Notification;

import static android.view.View.GONE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.R;

import java.util.Calendar;
import java.util.List;

public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.NotiViewHolder> {
    private List<Notification> mlistNoti;
    private ClickItem clickItem;

    public void setMlistNoti (List<Notification> list) {
        this.mlistNoti = list;
        notifyDataSetChanged();
    }

    public NotiAdapter(List<Notification> list, ClickItem clickItem) {
        this.mlistNoti = list;
        this.clickItem = clickItem;
    }

    public interface ClickItem {
        void onClickItem(int id);
    }

    @NonNull
    @Override
    public NotiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notification,parent,false);
        return new NotiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotiViewHolder holder, int position) {
        Notification notification = mlistNoti.get(position);
        if (notification == null) {
            return;
        }
        holder.tvTitle.setText(notification.getTitle());

        holder.tvTime1.setText(notification.getTime1());
        holder.tvTime2.setText(notification.getTime2());
        holder.tvTime3.setText(notification.getTime3());
        if (notification.getTime2()=="") {
            holder.tvTime2.setVisibility(GONE);
        } else if (notification.getTime3()=="") {
            holder.tvTime3.setVisibility(GONE);
        }

        checkClickDay(holder.daycn,notification.getDaycn());
        checkClickDay(holder.day2,notification.getDay2());
        checkClickDay(holder.day3,notification.getDay3());
        checkClickDay(holder.day4,notification.getDay4());
        checkClickDay(holder.day5,notification.getDay5());
        checkClickDay(holder.day6,notification.getDay6());
        checkClickDay(holder.day7,notification.getDay7());

        holder.switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.switchCompat.isChecked()) {
                    Log.e("switch","ok");

                } else {
                    Log.e("switch","ko");
                }
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItem.onClickItem(notification.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mlistNoti!=null) {
            return mlistNoti.size();
        }
        return 0;
    }

    public class NotiViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle,tvTime1,tvTime2,tvTime3,daycn,day2,day3,day4,day5,day6,day7;
        private SwitchCompat switchCompat;
        private CardView cardView;

        public NotiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.title_cardnoti);

            tvTime1 = itemView.findViewById(R.id.time1_cardnoti);
            tvTime2 = itemView.findViewById(R.id.time2_cardnoti);
            tvTime3 = itemView.findViewById(R.id.time3_cardnoti);

            daycn = itemView.findViewById(R.id.cardnoti_cn);
            day2 = itemView.findViewById(R.id.cardnoti_2);
            day3 = itemView.findViewById(R.id.cardnoti_3);
            day4 = itemView.findViewById(R.id.cardnoti_4);
            day5 = itemView.findViewById(R.id.cardnoti_5);
            day6 = itemView.findViewById(R.id.cardnoti_6);
            day7 = itemView.findViewById(R.id.cardnoti_7);

            switchCompat = itemView.findViewById(R.id.switch_cardnoti);

            cardView = itemView.findViewById(R.id.layout_card_hengio);
        }
    }

    private void checkClickDay(TextView textView, Boolean click) {
        if (click) {
            textView.setVisibility(View.VISIBLE);
        }
    }

}
