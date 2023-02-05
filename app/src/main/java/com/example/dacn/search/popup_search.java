package com.example.dacn.search;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;

import java.util.ArrayList;
import java.util.List;

public class popup_search extends Activity {

    List<BoDe> boDeArrayList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_search);

        recyclerView = findViewById(R.id.rvcodeList);
        recyclerView.setLayoutManager(new GridLayoutManager(popup_search.this,3, GridLayoutManager.VERTICAL, false));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        int Width = dm.widthPixels;
        int Height = dm.heightPixels;

        getWindow().setLayout((int)(Width*.9), (int)(Height*.5));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;
        getWindow().setAttributes(params);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        List<String> list = (List<String>) bundle.get("list");

        for (int i=0;i<list.size();i++) {
            boDeArrayList.add(new BoDe(list.get(i)));
        }
        ListCodeAdapter codeAdapter = new ListCodeAdapter(popup_search.this, boDeArrayList);
        recyclerView.setAdapter(codeAdapter);

        TruyenDuLieu.trMaDe = TruyenDuLieu.trMon + TruyenDuLieu.trLoaiDe;
    }
}