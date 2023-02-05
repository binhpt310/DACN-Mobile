package com.example.dacn.popup;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.R;
import com.example.dacn.search.CardModelDataSearch;
import com.example.dacn.search.ListCodeAdapter;
import com.example.dacn.search.SearchAdapter;
import com.example.dacn.search.search_question;

import java.util.ArrayList;

public class popup_search extends Activity {

    ArrayList<BoDe> boDeArrayList = new ArrayList<>();
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

        boDeArrayList = new ArrayList<BoDe>();
        boDeArrayList.add(new BoDe("1"));
        boDeArrayList.add(new BoDe("2"));
        boDeArrayList.add(new BoDe("3"));
        boDeArrayList.add(new BoDe("4"));
        boDeArrayList.add(new BoDe("5"));
        boDeArrayList.add(new BoDe("6"));
        boDeArrayList.add(new BoDe("7"));
        boDeArrayList.add(new BoDe("8"));
        boDeArrayList.add(new BoDe("9"));
        boDeArrayList.add(new BoDe("10"));
        boDeArrayList.add(new BoDe("11"));
        boDeArrayList.add(new BoDe("12"));
        ListCodeAdapter codeAdapter = new ListCodeAdapter(popup_search.this, boDeArrayList);
        recyclerView.setAdapter(codeAdapter);
    }
}