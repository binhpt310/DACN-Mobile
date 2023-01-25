package com.example.dacn.mucluc.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.dacn.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotificationsActivity extends AppCompatActivity {

    ImageView imgBack;
    FloatingActionButton btnAdd;
    RecyclerView rcvNotis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        khaibao();

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