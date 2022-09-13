package com.example.dacn;

import androidx.appcompat.app.AppCompatActivity;
import com.example.dacn.popup.*;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class thi_tracnghiem extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thi_tracnghiem);
        TextView time = findViewById(R.id.txt_time);

        //Call the timer
        reverseTimer(1, time);

        //Stop the timer
        cancelTimer();
    }

    CountDownTimer cTimer = null;
    void cancelTimer() {
        if(cTimer!=null)
            cTimer.cancel();
    }

    public void reverseTimer(int Seconds,final TextView tv){
        new CountDownTimer(Seconds* 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }
            public void onFinish() {
                tv.setText("00:00");
                Intent intent = new Intent(getApplicationContext(), popup_ket_thuc_thi_thu.class);
                startActivity(intent);
            }
        }.start();
    }
}