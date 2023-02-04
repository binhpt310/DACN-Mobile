package com.example.dacn.popup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.dacn.R;
import com.example.dacn.cauhoi.ontap_tracnghiem;
import com.example.dacn.cauhoi.thi_tracnghiem;
import com.example.dacn.hoanthanhbai.hoanthanhbaithi;

public class popup_hoan_thanh_thi_thu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_hoan_thanh_thi_thu);
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

        Button btn_lam_tiep = findViewById(R.id.button_lam_tiep);
        Button btn_hoan_thanh = findViewById(R.id.button_hoan_thanh);

        btn_lam_tiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(popup_hoan_thanh_thi_thu.this, hoanthanhbaithi.class);
                intent.setAction("Làm tiếp");
                LocalBroadcastManager.getInstance(popup_hoan_thanh_thi_thu.this).sendBroadcast(intent);
                finish();
            }
        });

        btn_hoan_thanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //callsendResultAPI();
                Intent intent = new Intent(popup_hoan_thanh_thi_thu.this, hoanthanhbaithi.class);
                intent.setAction("Lưu kết quả");
                LocalBroadcastManager.getInstance(popup_hoan_thanh_thi_thu.this).sendBroadcast(intent);

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
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
                LocalBroadcastManager.getInstance(popup_hoan_thanh_thi_thu.this).sendBroadcast(intent);
            }
        });
    }
}