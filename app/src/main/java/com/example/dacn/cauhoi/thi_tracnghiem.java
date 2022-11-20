package com.example.dacn.cauhoi;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.popup.*;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class thi_tracnghiem extends AppCompatActivity {

    TextView xemnhanh, socau, txt_toolbar;
    TextView[] ar_textview = new TextView[5];
    String[] ar_string = new String[6];
    ImageView btn_back;

    public int Cauhoihientai = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thi_tracnghiem);
        TextView time = findViewById(R.id.txt_time);

        LoadingDialog loadingDialog = new LoadingDialog(this);
        callDialog(loadingDialog);

        //truyền dữ liệu recyclerview ở trang trước qua
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        BoDe boDe = (BoDe) bundle.get("Truyền mã bộ đề");
        String MaBoDe = boDe.getCode();

        khaibao();

        String text = "Đề thi " + TruyenDuLieu.trTenMon + " - đề số " + MaBoDe;
        txt_toolbar.setText(text);

        //Call the timer
        reverseTimer(20, time);

        //Stop the timer
        cancelTimer();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(thi_tracnghiem.this, bo_de_thi.class);
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

                gan_gia_tri(adslist,ar_string,ar_textview);

                bamtracnghiem(ar_textview[1], ar_textview, ar_string[5]);
                bamtracnghiem(ar_textview[2], ar_textview, ar_string[5]);
                bamtracnghiem(ar_textview[3], ar_textview, ar_string[5]);
                bamtracnghiem(ar_textview[4], ar_textview, ar_string[5]);

                xemnhanh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cauhoihientai++;
                        if (Cauhoihientai < (adslist.size())){
                            gan_gia_tri(adslist,ar_string,ar_textview);
                        } else {
                            xemnhanh.setOnClickListener(null);
                        }
                        bamtracnghiem(ar_textview[1], ar_textview, ar_string[5]);
                        bamtracnghiem(ar_textview[2], ar_textview, ar_string[5]);
                        bamtracnghiem(ar_textview[3], ar_textview, ar_string[5]);
                        bamtracnghiem(ar_textview[4], ar_textview, ar_string[5]);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<CauHoiTracNghiem>> call, Throwable t) {
                Toast.makeText(thi_tracnghiem.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
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

    private void bamtracnghiem(TextView a, TextView tv[], String b) {
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = a.getText().toString().equals(b);
                if (result)
                    a.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                else {
                    a.setBackgroundResource(R.drawable.bg_otracnghiem_do);
                    for (int i=1;i<5;i++) {
                        if (tv[i].getText().toString().equals(b)) {
                            tv[i].setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                        }
                    }
                }
                Log.e("String", b);
                Log.e("Textview", a.getText().toString());
                Log.e("Dung sai", String.valueOf(result));
                for (int i=1;i<5;i++) {
                    tv[i].setOnClickListener(null);
                }
            }
        });

    }

    private void khaibao() {
        ar_textview[0] = findViewById(R.id.input_thi_cauhoi);
        ar_textview[1] = findViewById(R.id.thicautraloia);
        ar_textview[2] = findViewById(R.id.thicautraloib);
        ar_textview[3] = findViewById(R.id.thicautraloic);
        ar_textview[4] = findViewById(R.id.thicautraloid);
        xemnhanh = findViewById(R.id.btn_thi_xemnhanh);
        socau = findViewById(R.id.txt_thi_socauhoi);
        btn_back = findViewById(R.id.img_back_thi);
        txt_toolbar = findViewById(R.id.txt_toolbar_thi);
    }

    private void gan_gia_tri(List<CauHoiTracNghiem> a, String arg[], TextView tv[]) {
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

        //set câu hỏi hiện tại
        socau.setText(String.valueOf(Cauhoihientai+1));

        //xóa background ô đáp án khi đổi câu hỏi.
        for (int i = 1; i < 5; i++) {
            tv[i].setBackgroundResource(R.drawable.bg_otracnghiem);
        }
    }

    private void callDialog(LoadingDialog loadingDialog){
        loadingDialog.ShowDialog();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.DismissDialog();
            }
        },8000);
    }

}