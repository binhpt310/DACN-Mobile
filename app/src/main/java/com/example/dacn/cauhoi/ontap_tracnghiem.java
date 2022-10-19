package com.example.dacn.cauhoi;
import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.trangchu2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ontap_tracnghiem extends AppCompatActivity implements GestureDetector.OnGestureListener{

    TextView xemnhanh, socau, txt_toolbar;
    TextView[] ar_textview = new TextView[5];
    String[] ar_string = new String[6];
    ImageView btn_back, img_toi, img_lui;
    ImageView[] arr_img_progress = new ImageView[20];
    public int Cauhoihientai = 0;

    private float x1,x2,y1,y2;
    private static int MIN_DISTANCE = 150;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ontap_tracnghiem);

        //truyền dữ liệu recyclerview ở trang trước qua
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        BoDe boDe = (BoDe) bundle.get("Truyền mã bộ đề");
        String MaBoDe = boDe.getCode();

        khaibao();

        String text = "Ôn tập " + TruyenDuLieu.trTenMon + " - đề số " + MaBoDe;
        txt_toolbar.setText(text);

        //initialize gesturedetector
        gestureDetector = new GestureDetector(ontap_tracnghiem.this,this);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ontap_tracnghiem.this, bo_de_thi.class);
                startActivity(intent);
            }
        });

        HashMap<String, String> map = new HashMap<>();
        map.put("sub", TruyenDuLieu.trMaDe);
        map.put("Code", MaBoDe);
        Call<List<CauHoiTracNghiem>> call = retrofitInterface.getCauHoiTracNghiem(map);
        call.enqueue(new Callback<List<CauHoiTracNghiem>>() {
            @Override
            public void onResponse(Call<List<CauHoiTracNghiem>> call, Response<List<CauHoiTracNghiem>> response) {
                List<CauHoiTracNghiem> adslist = response.body();

                gan_gia_tri(adslist,ar_string,ar_textview,arr_img_progress[Cauhoihientai]);

                bamtracnghiem(ar_textview[1], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
                bamtracnghiem(ar_textview[2], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
                bamtracnghiem(ar_textview[3], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
                bamtracnghiem(ar_textview[4], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);

                img_toi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cauhoihientai++;
                        if (Cauhoihientai < (adslist.size())){
                            gan_gia_tri(adslist,ar_string,ar_textview,arr_img_progress[Cauhoihientai]);
                            if (Cauhoihientai == (adslist.size()-1)) {img_toi.setVisibility(View.INVISIBLE);}
                            if (Cauhoihientai > 0) {img_lui.setVisibility(View.VISIBLE);}
                        }
                        bamtracnghiem(ar_textview[1], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
                        bamtracnghiem(ar_textview[2], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
                        bamtracnghiem(ar_textview[3], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
                        bamtracnghiem(ar_textview[4], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
                    }
                });

                img_lui.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cauhoihientai--;
                        if (Cauhoihientai < (adslist.size())){
                            gan_gia_tri(adslist,ar_string,ar_textview,arr_img_progress[Cauhoihientai]);
                            if (Cauhoihientai == 0) {img_lui.setVisibility(View.INVISIBLE);}
                        }
                        bamtracnghiem(ar_textview[1], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
                        bamtracnghiem(ar_textview[2], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
                        bamtracnghiem(ar_textview[3], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
                        bamtracnghiem(ar_textview[4], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
                    }
                });


            }

            @Override
            public void onFailure(Call<List<CauHoiTracNghiem>> call, Throwable t) {
                Toast.makeText(ontap_tracnghiem.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }


    private void bamtracnghiem(TextView a, TextView tv[], String b, ImageView imageView) {
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = a.getText().toString().equals(b);
                if (result) {
                    a.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                    imageView.setImageResource(R.drawable.bg_line_xanh);
                }

                else {
                    a.setBackgroundResource(R.drawable.bg_otracnghiem_do);
                    imageView.setImageResource(R.drawable.bg_line_do);
                    for (int i=1;i<5;i++) {
                        if (tv[i].getText().toString().equals(b)) {
                            tv[i].setBackgroundResource(R.drawable.bg_otracnghiem_xanh);

                        }
                    }
                }
                /*Log.e("String", b);
                Log.e("Textview", a.getText().toString());
                Log.e("Dung sai", String.valueOf(result));*/
                for (int i=1;i<5;i++) {
                    tv[i].setOnClickListener(null);
                }

            }
        });

    }


    private void khaibao() {
        ar_textview[0] = findViewById(R.id.inputon_cauhoi);
        ar_textview[1] = findViewById(R.id.oncautraloia);
        ar_textview[2] = findViewById(R.id.oncautraloib);
        ar_textview[3] = findViewById(R.id.oncautraloic);
        ar_textview[4] = findViewById(R.id.oncautraloid);
        xemnhanh = findViewById(R.id.btn_on_xemnhanh);
        socau = findViewById(R.id.txt_on_socauhoi);
        btn_back = findViewById(R.id.img_back_ontap);
        txt_toolbar = findViewById(R.id.text_toolbar_ontap);

        arr_img_progress[0] = findViewById(R.id.progress_1);
        arr_img_progress[1] = findViewById(R.id.progress_2);
        arr_img_progress[2] = findViewById(R.id.progress_3);
        arr_img_progress[3] = findViewById(R.id.progress_4);
        arr_img_progress[4] = findViewById(R.id.progress_5);
        arr_img_progress[5] = findViewById(R.id.progress_6);
        arr_img_progress[6] = findViewById(R.id.progress_7);
        arr_img_progress[7] = findViewById(R.id.progress_8);
        arr_img_progress[8] = findViewById(R.id.progress_9);
        arr_img_progress[9] = findViewById(R.id.progress_10);
        arr_img_progress[10] = findViewById(R.id.progress_11);
        arr_img_progress[11] = findViewById(R.id.progress_12);
        arr_img_progress[12] = findViewById(R.id.progress_13);
        arr_img_progress[13] = findViewById(R.id.progress_14);
        arr_img_progress[14] = findViewById(R.id.progress_15);
        arr_img_progress[15] = findViewById(R.id.progress_16);
        arr_img_progress[16] = findViewById(R.id.progress_17);
        arr_img_progress[17] = findViewById(R.id.progress_18);
        arr_img_progress[18] = findViewById(R.id.progress_19);
        arr_img_progress[19] = findViewById(R.id.progress_20);

        img_toi = findViewById(R.id.img_toi);
        img_lui = findViewById(R.id.img_lui);
    }

    private void gan_gia_tri(List<CauHoiTracNghiem> a, String arg[], TextView tv[], ImageView imageView) {
        arg[0] = a.get(Cauhoihientai).getQuestion();
        arg[1] = a.get(Cauhoihientai).getA();
        arg[2] = a.get(Cauhoihientai).getB();
        arg[3] = a.get(Cauhoihientai).getC();
        arg[4] = a.get(Cauhoihientai).getD();
        arg[5] = a.get(Cauhoihientai).getAnw();

        tv[0].setText(arg[0]);
        tv[1].setText(arg[1]);
        tv[2].setText(arg[2]);
        tv[3].setText(arg[3]);
        tv[4].setText(arg[4]);

        //random vị trí textview để mảng dữ liệu đưa vào
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<5; i++) list.add(i);

        Collections.shuffle(list);
        for (int i=0; i<4; i++) {
            tv[i+1].setText(arg[(list.get(i))]);
            Log.e("dd", String.valueOf((list.get(i))));
        }

        //hiển thị số câu
        socau.setText(String.valueOf(Cauhoihientai+1));

        //đang ở câu nào thì set trắng ngay img đó
        imageView.setImageResource(R.drawable.bg_line_trang);

        //xóa background ô đáp án khi đổi câu hỏi.
        for (int i = 1; i < 5; i++) {
            tv[i].setBackgroundResource(R.drawable.bg_otracnghiem);
        }
    }

    //quẹt qua lại chuyển câu
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                float valueX = x2 - x1;

                float valueY = y2 - y1;

                if (Math.abs(valueX) > MIN_DISTANCE) {
                    if (x2>x1) {
                        if (Cauhoihientai>0) {
                            Cauhoihientai--;
                            Log.e("e", "Right Swipe" + Cauhoihientai);
                        }

                    } else {
                        Cauhoihientai++;
                        Log.e("e", "Left Swipe" + Cauhoihientai);
                    }
                }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
