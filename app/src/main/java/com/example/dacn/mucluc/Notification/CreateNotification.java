package com.example.dacn.mucluc.Notification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.dacn.R;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateNotification extends AppCompatActivity {

    ImageView imgClose,imgDone;
    EditText edTitle;
    Spinner spinner;
    TextView time1,time2,time3,daycn,day2,day3,day4,day5,day6,day7;
    LinearLayout llTime1,llTime2,llTime3;

    Calendar myCalender;
    int hour,minute;

    Boolean clickDay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);

        khaibao();

        //time
        myCalender = Calendar.getInstance();
        hour = myCalender.get(Calendar.HOUR_OF_DAY);
        minute = myCalender.get(Calendar.MINUTE);
        String time = hour+":"+minute;

        //spinner
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("abc",arrayList.get(i));
                switch (arrayList.get(i)) {
                    case "1":
                        llTime1.setVisibility(View.VISIBLE);
                        llTime2.setVisibility(View.GONE);
                        llTime3.setVisibility(View.GONE);
                        time1.setText(time);
                        time2.setText(null);
                        time3.setText(null);
                        break;
                    case "2":
                        llTime1.setVisibility(View.VISIBLE);
                        llTime2.setVisibility(View.VISIBLE);
                        llTime3.setVisibility(View.GONE);
                        time1.setText(time);
                        time2.setText(time);
                        time3.setText(null);
                        break;
                    case "3":
                        llTime1.setVisibility(View.VISIBLE);
                        llTime2.setVisibility(View.VISIBLE);
                        llTime3.setVisibility(View.VISIBLE);
                        time1.setText(time);
                        time2.setText(time);
                        time3.setText(time);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        clicktime(time1);
        clicktime(time2);
        clicktime(time3);

        clickday(daycn);
        clickday(day2);
        clickday(day3);
        clickday(day4);
        clickday(day5);
        clickday(day6);
        clickday(day7);

        imgDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strTitle = edTitle.getText().toString().trim();

                String strTime1 = time1.getText().toString().trim();
                String strTime2 = time2.getText().toString().trim();
                String strTime3 = time3.getText().toString().trim();
                Notification notification = new Notification(strTitle,strTime1,strTime2,strTime3,"cn","2","2","2","2","2","2");
                NotiDatabase.getInstance(CreateNotification.this).notiDAO().insertNoti(notification);
                Log.e("CreateNoti", "ok");

                startActivity(new Intent(CreateNotification.this,NotificationsActivity.class));
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateNotification.this,NotificationsActivity.class));
            }
        });
    }

    private void khaibao() {
        imgClose = findViewById(R.id.img_close_create);
        imgDone = findViewById(R.id.img_done_create);

        edTitle = findViewById(R.id.ed_tieude_create);
        spinner = findViewById(R.id.spiner_create);

        time1 = findViewById(R.id.txt_time1_create);
        time2 = findViewById(R.id.txt_time2_create);
        time3 = findViewById(R.id.txt_time3_create);

        daycn = findViewById(R.id.tv_cn);
        day2 = findViewById(R.id.tv_2);
        day3 = findViewById(R.id.tv_3);
        day4 = findViewById(R.id.tv_4);
        day5 = findViewById(R.id.tv_5);
        day6 = findViewById(R.id.tv_6);
        day7 = findViewById(R.id.tv_7);

        llTime1 = findViewById(R.id.layout1_create);
        llTime2 = findViewById(R.id.layout2_create);
        llTime3 = findViewById(R.id.layout3_create);
    }

    public void showTimePickerDialog(TextView textView) {
        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay +":"+ minute;
                textView.setText(time);
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(CreateNotification.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, true);
        timePickerDialog.setTitle("Chọn giờ");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }

    private void clicktime(TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(textView);
            }
        });
    }

    private void clickday(TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickDay == false) {
                    textView.setBackgroundResource(R.drawable.bg_khung_xam);
                    textView.setTextColor(getResources().getColor(R.color.trang));
                    Log.e("click","ok");
                    clickDay = true;
                } else if (clickDay == true) {
                    textView.setBackgroundResource(R.drawable.bg_khung_otp);
                    textView.setTextColor(getResources().getColor(R.color.den80));
                    Log.e("click","ko ok");
                    clickDay = false;
                }
            }
        });
    }

}