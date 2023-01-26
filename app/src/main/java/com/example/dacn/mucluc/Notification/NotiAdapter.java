package com.example.dacn.mucluc.Notification;

import static android.view.View.GONE;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.R;

import java.util.List;

public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.NotiViewHolder> {
    private List<Notification> mlistNoti;

    public void setMlistNoti (List<Notification> list) {
        this.mlistNoti = list;
        notifyDataSetChanged();
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
        if (notification.getTime2()==null) {
            holder.tvTime2.setVisibility(GONE);
        } else if (notification.getTime3()==null) {
            holder.tvTime3.setVisibility(GONE);
        }

        holder.daycn.setText(notification.getDaycn());
        holder.day2.setText(notification.getDay2());
        holder.day3.setText(notification.getDay3());
        holder.day4.setText(notification.getDay4());
        holder.day5.setText(notification.getDay5());
        holder.day6.setText(notification.getDay6());
        holder.day7.setText(notification.getDay7());

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


        }
    }
}
