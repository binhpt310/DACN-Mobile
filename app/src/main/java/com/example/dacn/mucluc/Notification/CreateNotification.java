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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreateNotification extends AppCompatActivity {

    ImageView imgClose,imgDone;
    EditText edTitle;
    Spinner spinner;
    TextView[] day = new TextView[7];
    TextView tvTime1,tvTime2,tvTime3;
    LinearLayout llTime1,llTime2,llTime3;

    Calendar myCalender;
    SimpleDateFormat simpleDateFormat;
    int hour,minute;
    String time,time1,time2,time3,timeTamp;
    ArrayList<String> arrayListSpinner = new ArrayList<>();

    Boolean[] click = {false,false,false,false,false,false,false};

    private int notiId=-1;
    Notification notiTemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notification);

        khaibao();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            notiId = (int) bundle.get("id_noti");
        }
        notiTemp = NotiDatabase.getInstance(CreateNotification.this).notiDAO().getSpecificNote(notiId);

        //time
        myCalender = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH:mm");
        hour = myCalender.get(Calendar.HOUR_OF_DAY);
        minute = myCalender.get(Calendar.MINUTE);

        time = String.format("%02d",hour) + ":" + String.format("%02d",minute);


        //spinner
        arrayListSpinner.add("1");
        arrayListSpinner.add("2");
        arrayListSpinner.add("3");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,arrayListSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (arrayListSpinner.get(i)) {
                    case "1":
                        llTime1.setVisibility(View.VISIBLE);
                        llTime2.setVisibility(View.GONE);
                        llTime3.setVisibility(View.GONE);
                        if (notiId!=-1) {
                            tvTime1.setText(notiTemp.getTime1());
                            tvTime2.setText("");
                            tvTime3.setText("");
                        } else {
                            tvTime1.setText(time);
                            tvTime2.setText("");
                            tvTime3.setText("");
                        }
                        break;
                    case "2":
                        llTime1.setVisibility(View.VISIBLE);
                        llTime2.setVisibility(View.VISIBLE);
                        llTime3.setVisibility(View.GONE);
                        if (notiId!=-1) {
                            tvTime1.setText(notiTemp.getTime1());
                            tvTime2.setText(notiTemp.getTime2());
                            tvTime3.setText("");
                        } else {
                            tvTime1.setText(time);
                            tvTime2.setText(time);
                            tvTime3.setText("");
                        }
                        break;
                    case "3":
                        llTime1.setVisibility(View.VISIBLE);
                        llTime2.setVisibility(View.VISIBLE);
                        llTime3.setVisibility(View.VISIBLE);
                        if (notiId!=-1) {
                            tvTime1.setText(notiTemp.getTime1());
                            tvTime2.setText(notiTemp.getTime2());
                            tvTime3.setText(notiTemp.getTime3());
                        } else {
                            tvTime1.setText(time);
                            tvTime2.setText(time);
                            tvTime3.setText(time);
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        clicktime(tvTime1);
        clicktime(tvTime2);
        clicktime(tvTime3);

        imgDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notiId!=-1) {
                    //set title
                    notiTemp.setTitle(edTitle.getText().toString());

                    //set time
                    notiTemp.setTime1(tvTime1.getText().toString());
                    notiTemp.setTime2(tvTime2.getText().toString());
                    notiTemp.setTime3(tvTime3.getText().toString());

                    //set day in void onClick
                    NotiDatabase.getInstance(CreateNotification.this).notiDAO().updateNoti(notiTemp);
                } else {
                    String strTitle = edTitle.getText().toString().trim();

                    time1 = tvTime1.getText().toString().trim();
                    time2 = tvTime2.getText().toString().trim();
                    time3 = tvTime3.getText().toString().trim();

                    Notification notification = new Notification(strTitle,time1,time2,time3,click[0],click[1],click[2],click[3],click[4],click[5],click[6]);
                    NotiDatabase.getInstance(CreateNotification.this).notiDAO().insertNoti(notification);
                }
                startActivity(new Intent(CreateNotification.this,NotificationsActivity.class));
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateNotification.this,NotificationsActivity.class));
            }
        });

        if (notiId!=-1) {
            //get title
            edTitle.setText(notiTemp.getTitle());

            //get day
            checkDay(day[0],notiTemp.getDaycn());
            checkDay(day[1],notiTemp.getDay2());
            checkDay(day[2],notiTemp.getDay3());
            checkDay(day[3],notiTemp.getDay4());
            checkDay(day[4],notiTemp.getDay5());
            checkDay(day[5],notiTemp.getDay6());
            checkDay(day[6],notiTemp.getDay7());

            //get time
            if (notiTemp.getTime2().equals("") && notiTemp.getTime3().equals(""))  {
                spinner.setSelection(0);
            } else if (!notiTemp.getTime2().equals("") && notiTemp.getTime3().equals(""))  {
                spinner.setSelection(1);
            } else if (!notiTemp.getTime2().equals("") && !notiTemp.getTime3().equals(""))  {
                spinner.setSelection(2);
            }
        }
    }

    private void khaibao() {
        imgClose = findViewById(R.id.img_close_create);
        imgDone = findViewById(R.id.img_done_create);

        edTitle = findViewById(R.id.ed_tieude_create);
        spinner = findViewById(R.id.spiner_create);

        tvTime1 = findViewById(R.id.txt_time1_create);
        tvTime2 = findViewById(R.id.txt_time2_create);
        tvTime3 = findViewById(R.id.txt_time3_create);

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
                timeTamp = String.format("%02d",hourOfDay) + ":" + String.format("%02d",minute);
                textView.setText(timeTamp);
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
        if (notiId!=-1) {
            if (notiTemp.getDaycn()) {
                clickDayTrue(day[0]);
                notiTemp.setDaycn(false);
            } else {
                clickDayFalse(day[0]);
                notiTemp.setDaycn(true);
            }
        } else {
            if (click[0]) {
                clickDayTrue(day[0]);
                click[0] = false;
            } else {
                clickDayFalse(day[0]);
                click[0] = true;
            }
        }
    }

    public void click2(View v) {
        if (notiId!=-1) {
            if (notiTemp.getDay2()) {
                clickDayTrue(day[1]);
                notiTemp.setDay2(false);
            } else {
                clickDayFalse(day[1]);
                notiTemp.setDay2(true);
            }
        } else {
            if (click[1]) {
                clickDayTrue(day[1]);
                click[1] = false;
            } else {
                clickDayFalse(day[1]);
                click[1] = true;
            }
        }
    }

    public void click3(View v) {
        if (notiId!=-1) {
            if (notiTemp.getDay3()) {
                clickDayTrue(day[2]);
                notiTemp.setDay3(false);
            } else {
                clickDayFalse(day[2]);
                notiTemp.setDay3(true);
            }
        } else {
            if (click[2]) {
                clickDayTrue(day[2]);
                click[2] = false;
            } else {
                clickDayFalse(day[2]);
                click[2] = true;
            }
        }
    }

    public void click4(View v) {
        if (notiId!=-1) {
            if (notiTemp.getDay4()) {
                clickDayTrue(day[3]);
                notiTemp.setDay4(false);
            } else {
                clickDayFalse(day[3]);
                notiTemp.setDay4(true);
            }
        } else {
            if (click[3]) {
                clickDayTrue(day[3]);
                click[3] = false;
            } else {
                clickDayFalse(day[3]);
                click[3] = true;
            }
        }
    }

    public void click5(View v) {
        if (notiId!=-1) {
            if (notiTemp.getDay5()) {
                clickDayTrue(day[4]);
                notiTemp.setDay5(false);
            } else {
                clickDayFalse(day[4]);
                notiTemp.setDay5(true);
            }
        } else {
            if (click[4]) {
                clickDayTrue(day[4]);
                click[4] = false;
            } else {
                clickDayFalse(day[4]);
                click[4] = true;
            }
        }
    }

    public void click6(View v) {
        if (notiId!=-1) {
            if (notiTemp.getDay6()) {
                clickDayTrue(day[5]);
                notiTemp.setDay6(false);
            } else {
                clickDayFalse(day[5]);
                notiTemp.setDay6(true);
            }
        } else {
            if (click[5]) {
                clickDayTrue(day[5]);
                click[5] = false;
            } else {
                clickDayFalse(day[5]);
                click[5] = true;
            }
        }
    }

    public void click7(View v) {
        if (notiId!=-1) {
            if (notiTemp.getDay7()) {
                clickDayTrue(day[6]);
                notiTemp.setDay7(false);
            } else {
                clickDayFalse(day[6]);
                notiTemp.setDay7(true);
            }
        } else {
            if (click[6]) {
                clickDayTrue(day[6]);
                click[6] = false;
            } else {
                clickDayFalse(day[6]);
                click[6] = true;
            }
        }
    }

    public void clickDayTrue(TextView textView) {
        textView.setBackgroundResource(R.drawable.bg_khung_otp);
        textView.setTextColor(getResources().getColor(R.color.den80));
    }

    public void clickDayFalse(TextView textView) {
        textView.setBackgroundResource(R.drawable.bg_khung_xam);
        textView.setTextColor(getResources().getColor(R.color.trang));
    }

    public void checkDay (TextView textView, Boolean check) {
        if (check) {
            clickDayFalse(textView);
        } else {
            clickDayTrue(textView);
        }
    }

}