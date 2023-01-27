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
    TextView[] day = new TextView[7];
    TextView time1,time2,time3;
    LinearLayout llTime1,llTime2,llTime3;

    Calendar myCalender;
    int hour,minute;

    Boolean[] click = {false,false,false,false,false,false,false};

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

        imgDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strTitle = edTitle.getText().toString().trim();

                String strTime1 = time1.getText().toString().trim();
                String strTime2 = time2.getText().toString().trim();
                String strTime3 = time3.getText().toString().trim();

                Notification notification = new Notification(strTitle,strTime1,strTime2,strTime3,click[0],click[1],click[2],click[3],click[4],click[5],click[6]);
                NotiDatabase.getInstance(CreateNotification.this).notiDAO().insertNoti(notification);

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

        day[0] = findViewById(R.id.tv_cn);
        day[1] = findViewById(R.id.tv_2);
        day[2] = findViewById(R.id.tv_3);
        day[3] = findViewById(R.id.tv_4);
        day[4] = findViewById(R.id.tv_5);
        day[5] = findViewById(R.id.tv_6);
        day[6] = findViewById(R.id.tv_7);

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

    public void clickCn(View v) {
        if (click[0]) {
            day[0].setBackgroundResource(R.drawable.bg_khung_otp);
            day[0].setTextColor(getResources().getColor(R.color.den80));
            Log.e("click","ok");
            click[0] = false;
        } else {
            day[0].setBackgroundResource(R.drawable.bg_khung_xam);
            day[0].setTextColor(getResources().getColor(R.color.trang));
            Log.e("click","ko");
            click[0] = true;
        }
    }

    public void click2(View v) {
        if (click[1]) {
            day[1].setBackgroundResource(R.drawable.bg_khung_otp);
            day[1].setTextColor(getResources().getColor(R.color.den80));
            Log.e("click","ok");
            click[1] = false;
        } else {
            day[1].setBackgroundResource(R.drawable.bg_khung_xam);
            day[1].setTextColor(getResources().getColor(R.color.trang));
            Log.e("click","ko");
            click[1] = true;
        }
    }

    public void click3(View v) {
        if (click[2]) {
            day[2].setBackgroundResource(R.drawable.bg_khung_otp);
            day[2].setTextColor(getResources().getColor(R.color.den80));
            Log.e("click","ok");
            click[2] = false;
        } else {
            day[2].setBackgroundResource(R.drawable.bg_khung_xam);
            day[2].setTextColor(getResources().getColor(R.color.trang));
            Log.e("click","ko");
            click[2] = true;
        }
    }

    public void click4(View v) {
        if (click[3]) {
            day[3].setBackgroundResource(R.drawable.bg_khung_otp);
            day[3].setTextColor(getResources().getColor(R.color.den80));
            Log.e("click","ok");
            click[3] = false;
        } else {
            day[3].setBackgroundResource(R.drawable.bg_khung_xam);
            day[3].setTextColor(getResources().getColor(R.color.trang));
            Log.e("click","ko");
            click[3] = true;
        }
    }

    public void click5(View v) {
        if (click[4]) {
            day[4].setBackgroundResource(R.drawable.bg_khung_otp);
            day[4].setTextColor(getResources().getColor(R.color.den80));
            Log.e("click","ok");
            click[4] = false;
        } else {
            day[4].setBackgroundResource(R.drawable.bg_khung_xam);
            day[4].setTextColor(getResources().getColor(R.color.trang));
            Log.e("click","ko");
            click[4] = true;
            Log.e("false", String.valueOf(click[4]));
        }
    }

    public void click6(View v) {
        if (click[5]) {
            day[5].setBackgroundResource(R.drawable.bg_khung_otp);
            day[5].setTextColor(getResources().getColor(R.color.den80));
            Log.e("click","ok");
            click[5] = false;
        } else {
            day[5].setBackgroundResource(R.drawable.bg_khung_xam);
            day[5].setTextColor(getResources().getColor(R.color.trang));
            Log.e("click","ko");
            click[5] = true;
        }
    }

    public void click7(View v) {
        if (click[6]) {
            day[6].setBackgroundResource(R.drawable.bg_khung_otp);
            day[6].setTextColor(getResources().getColor(R.color.den80));
            Log.e("click","ok");
            click[6] = false;
        } else {
            day[6].setBackgroundResource(R.drawable.bg_khung_xam);
            day[6].setTextColor(getResources().getColor(R.color.trang));
            Log.e("click","ko");
            click[6] = true;
        }
    }

}