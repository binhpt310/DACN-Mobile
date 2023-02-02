package com.example.dacn.mucluc.Notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dacn.R;
import com.example.dacn.mucluc.mucluc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    ImageView imgBack;
    FloatingActionButton btnAdd;
    RecyclerView rcvNotis;

    private NotiAdapter notiAdapter;
    private List<Notification> mListNoti = new ArrayList<>();

    Notification notiTemp;

    Calendar myCalender;
    int hour,minute;
    String time;

    AlarmManager alarmManager;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        khaibao();

        //time
        myCalender = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        hour = myCalender.get(Calendar.HOUR_OF_DAY);
        minute = myCalender.get(Calendar.MINUTE);
        time = String.format("%02d",hour) + ":" + String.format("%02d",minute);

        notiAdapter = new NotiAdapter(mListNoti, new NotiAdapter.ClickItem() {
            @Override
            public void onClickItem(int id) {
                Intent intent = new Intent(NotificationsActivity.this, CreateNotification.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("id_noti", id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }, new NotiAdapter.ClickItemSwitchOn() {
            @Override
            public void onClickItemSwitchOn(int id, Boolean b) {
                notiTemp = NotiDatabase.getInstance(NotificationsActivity.this).notiDAO().getSpecificNote(id);
                notiTemp.setCheckswitch(b);
                NotiDatabase.getInstance(NotificationsActivity.this).notiDAO().updateNoti(notiTemp);

            }
        }, new NotiAdapter.ClickItemSwitchOff() {
            @Override
            public void onClickItemSwitchOff(int id, Boolean b) {
                notiTemp = NotiDatabase.getInstance(NotificationsActivity.this).notiDAO().getSpecificNote(id);
                notiTemp.setCheckswitch(b);
                NotiDatabase.getInstance(NotificationsActivity.this).notiDAO().updateNoti(notiTemp);
            }
        });

        rcvNotis.setLayoutManager(new LinearLayoutManager(this));
        rcvNotis.setAdapter(notiAdapter);

        updateDatabase();

        for (int i=0;i<mListNoti.size();i++) {
            if (mListNoti.get(i).getCheckswitch()) {
                alarmClock(mListNoti.get(i).getTitle(),mListNoti.get(i).getTime1());
                if (mListNoti.get(i).getTime2()!="") {
                    alarmClock(mListNoti.get(i).getTitle(),mListNoti.get(i).getTime2());
                } else if (mListNoti.get(i).getTime3()!="") {
                    alarmClock(mListNoti.get(i).getTitle(),mListNoti.get(i).getTime3());
                }
            }
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationsActivity.this, CreateNotification.class);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationsActivity.this, mucluc.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
            }
        });

        //Swipe to Delete
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvNotis.addItemDecoration(itemDecoration);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Notification notiDelete = mListNoti.get(viewHolder.getAdapterPosition());
                NotiDatabase.getInstance(NotificationsActivity.this).notiDAO().deleteNoti(notiDelete);
                updateDatabase();

            }
        });
        itemTouchHelper.attachToRecyclerView(rcvNotis);
    }

    private void updateDatabase() {
        mListNoti = NotiDatabase.getInstance(NotificationsActivity.this).notiDAO().getListNoti();
        notiAdapter.setMlistNoti(mListNoti);
    }

    private void khaibao() {
        imgBack = findViewById(R.id.img_back_noti);
        btnAdd = findViewById(R.id.btn_add_notifications);
        rcvNotis = findViewById(R.id.rcv_notifications);
    }

    private void alarmClock(String title, String timeNoti) {
        if (timeNoti.equals(time)) {
            sendNoti(title);
        }
    }

    private void sendNoti(String message) {
        android.app.Notification notification = new NotificationCompat.Builder(this,BuildVersion.CHANNEL_ID)
                .setContentTitle("Nhắc nhở")
                .setContentText(message)
                .setSmallIcon(R.drawable.img_knowledge)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(getNotiId(),notification);
        }
    }

    private int getNotiId() {
        return (int) new Date().getTime();
    }

}