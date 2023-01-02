package com.example.dacn.TheHocTap.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import android.os.Bundle;

import com.example.dacn.R;
import com.example.dacn.TheHocTap.Fragment.FavoritesFragment;
import com.example.dacn.TheHocTap.Fragment.NoteFragment;
import com.example.dacn.TheHocTap.Fragment.TrashFragment;
import com.example.dacn.TheHocTap.Util.GeneralUtil;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NoteMainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "NoteChannel";
    private Context mContext;
    private FloatingActionMenu fam;
    private int ctr = 0;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_main);
        mContext = this;

        // Create notification channel
        createNotificationChannel();

        Toolbar myToolbar = findViewById(R.id.toolbar);
        myToolbar.setTitle("");
        setSupportActionBar(myToolbar);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorAccent));

        BottomNavigationView navView = findViewById(R.id.bottom_nav);
        navView.setItemIconTintList(null);

        navView.setOnNavigationItemSelectedListener(navListener);

        if (getIntent().getBooleanExtra("trash", false)) { // Start trash fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new TrashFragment()).commit();
            navView.getMenu().getItem(2).setChecked(true);
        } else if (getIntent().getBooleanExtra("favorite", false)){ // Start favs fragment
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new FavoritesFragment()).commit();
            navView.getMenu().getItem(1).setChecked(true);
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new NoteFragment()).commit();
            navView.getMenu().getItem(0).setChecked(true);
        }

        fam = findViewById(R.id.fam);
        FloatingActionButton fabNote = findViewById(R.id.fab_item_note);
        fabNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeneralUtil.goToActivity(EditActivity.class, mContext);
            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFrag = null;
                    switch (item.getItemId()){
                        case R.id.nav_notes:
                            selectedFrag = new NoteFragment();
                            break;

                        case R.id.nav_fav:
                            selectedFrag = new FavoritesFragment();
                            break;

                        case R.id.nav_trash:
                            selectedFrag = new TrashFragment();
                            break;
                    }

                    if (selectedFrag == null) selectedFrag = new NoteFragment();
                    if (fam.isOpened()) fam.close(true);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_layout, selectedFrag).commit();

                    return true;
                }
            };

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    @Override
    public void onBackPressed(){
        FloatingActionMenu fam = findViewById(R.id.fam);
        if (fam.isOpened()) {
            fam.close(true);
            ctr = 0;
            return;
        }

        if (ctr > 0) {
            super.onBackPressed();
        } else {
            ctr++;
            Toast.makeText(this, "Nhấn lần nữa để thoát", Toast.LENGTH_SHORT).show();
        }
    }
}