package com.example.dacn.mucluc.Notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dacn.R;
import com.example.dacn.mucluc.mucluc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {

    ImageView imgBack;
    FloatingActionButton btnAdd;
    RecyclerView rcvNotis;

    private NotiAdapter notiAdapter = new NotiAdapter();
    private List<Notification> mListNoti = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        khaibao();

        rcvNotis.setLayoutManager(new LinearLayoutManager(this));
        rcvNotis.setAdapter(notiAdapter);

        updateDatabase();

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
}