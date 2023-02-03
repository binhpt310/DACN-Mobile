package com.example.dacn.popup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

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

import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.R;
import com.example.dacn.hoanthanhbai.hoanthanhbaithi;

public class popup_tro_ve extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_tro_ve);
        setFinishOnTouchOutside(false);
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

        Button btn_luu = findViewById(R.id.button_luu);
        Button btn_thoat = findViewById(R.id.button_thoat);

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(popup_tro_ve.this, hoanthanhbaithi.class);
                intent.setAction("Làm tiếp");
                LocalBroadcastManager.getInstance(popup_tro_ve.this).sendBroadcast(intent);
                finish();
            }
        });

        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(popup_tro_ve.this, bo_de_thi.class);
                startActivity(intent);;
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
            }
        });
    }

    private void bamButton(Button btn, int i) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("Lưu kết quả");
                intent.putExtra("id",i);
                LocalBroadcastManager.getInstance(popup_tro_ve.this).sendBroadcast(intent);
            }
        });
    }
}