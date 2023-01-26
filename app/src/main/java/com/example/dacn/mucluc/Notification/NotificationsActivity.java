package com.example.dacn.mucluc.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.dacn.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    ImageView imgBack;
    FloatingActionButton btnAdd;
    RecyclerView rcvNotis;

    private NotiAdapter notiAdapter;
    private List<Notification> mListNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        khaibao();

        notiAdapter = new NotiAdapter();
        mListNoti = new ArrayList<>();
        notiAdapter.setMlistNoti(mListNoti);
        rcvNotis.setLayoutManager(new LinearLayoutManager(this));
        rcvNotis.setAdapter(notiAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationsActivity.this,create_notification.class);
                startActivity(intent);
            }
        });
    }

    private void khaibao() {
        imgBack = findViewById(R.id.img_back_noti);
        btnAdd = findViewById(R.id.btn_add_notifications);
        rcvNotis = findViewById(R.id.rcv_notifications);
    }
}