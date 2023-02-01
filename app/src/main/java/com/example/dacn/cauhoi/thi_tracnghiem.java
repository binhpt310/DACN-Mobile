package com.example.dacn.cauhoi;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.popup.*;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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

    TextView xemnhanh, socau, txt_toolbar, time;
    TextView[] ar_textview = new TextView[5];
    String[] ar_string = new String[7]; //0 cauhoi, 1-4 dapan, 5 dapandung, 6 cauchon
    ImageView btn_back, img_toi, img_lui, btn_done;

    public int Cauhoihientai = 0, Diem = 0, socauchualam = 40, socaudalam = 0;

    TextView[] ar_tv_bottom = new TextView[40];
    String MaBoDe;

    ProgressDialog progressdialog;

    public String[] chondapan = new String[40];
    List<CauHoiTracNghiem> adslist = new ArrayList<CauHoiTracNghiem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thi_tracnghiem);

        /*LoadingDialog loadingDialog = new LoadingDialog(this);
        callDialog(loadingDialog);*/

        progressdialog = new ProgressDialog(thi_tracnghiem.this);
        progressdialog.setMessage("Loadinggg");

        //truyền dữ liệu recyclerview ở trang trước qua
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        BoDe boDe = (BoDe) bundle.get("Truyền mã bộ đề");
        MaBoDe = boDe.getCode();

        khaibao();

        //thông tin đề trên toolbar
        String text = "Đề thi " + TruyenDuLieu.trTenMon + " - đề số " + MaBoDe;
        txt_toolbar.setText(text);

        //Call the timer
        reverseTimer(20, time);

        //Stop the timer
        cancelTimer();

        //20 giá trị rỗng cho mảng chọn đáp án
        for (int i = 0; i < chondapan.length; i++) {
            chondapan[i] = "";
        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(thi_tracnghiem.this, bo_de_thi.class);
                startActivity(intent);
            }
        });

        xemnhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        callApi();

        IntentFilter filter = new IntentFilter();
        filter.addAction("Trắc nghiệm thi");
        BroadcastReceiver mRefreshReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("Trắc nghiệm thi")) {
                    Cauhoihientai = intent.getIntExtra("id",-1);
                    gan_gia_tri(adslist,ar_string,ar_textview);
                    bamTracNghiem(adslist);
                }
            }
        };
        LocalBroadcastManager.getInstance(thi_tracnghiem.this).registerReceiver(mRefreshReceiver, filter);

        img_toi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cauhoihientai++;
                if (Cauhoihientai < (adslist.size())){
                    gan_gia_tri(adslist,ar_string,ar_textview);
                    img_toi.setVisibility(View.VISIBLE);
                    img_lui.setVisibility(View.VISIBLE);
                    if (Cauhoihientai == (adslist.size()-1)) {img_toi.setVisibility(View.INVISIBLE);}
                    else if (Cauhoihientai == 0) {img_lui.setVisibility(View.INVISIBLE);}
                }
                bamTracNghiem(adslist);
            }
        });

        img_lui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cauhoihientai--;
                if (Cauhoihientai < (adslist.size())){
                    gan_gia_tri(adslist,ar_string,ar_textview);
                    img_toi.setVisibility(View.VISIBLE);
                    img_lui.setVisibility(View.VISIBLE);
                    if (Cauhoihientai == (adslist.size()-1)) {img_toi.setVisibility(View.INVISIBLE);}
                    else if (Cauhoihientai == 0) {img_lui.setVisibility(View.INVISIBLE);}
                }
                bamTracNghiem(adslist);
            }
        });

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendResult(); //chưa xử lý time
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
        txt_toolbar = findViewById(R.id.txt_toolbar_thi);

        btn_back = findViewById(R.id.img_back_thi);
        btn_done = findViewById(R.id.img_out_thi);
        img_toi = findViewById(R.id.img_toi_thi);
        img_lui = findViewById(R.id.img_lui_thi);

        time = findViewById(R.id.txt_time);
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

    public void callApi () {
        progressdialog.show();
        HashMap<String, String> map = new HashMap<>();
        map.put("sub", TruyenDuLieu.trMaDe);
        map.put("Code", MaBoDe);
        Call<List<CauHoiTracNghiem>> call = retrofitInterface.getCauHoiTracNghiem(map);
        call.enqueue(new Callback<List<CauHoiTracNghiem>>() {
            @Override
            public void onResponse(Call<List<CauHoiTracNghiem>> call, Response<List<CauHoiTracNghiem>> response) {
                adslist = response.body();

                progressdialog.dismiss();

                gan_gia_tri(adslist,ar_string,ar_textview);
                bamTracNghiem(adslist);
            }

            @Override
            public void onFailure(Call<List<CauHoiTracNghiem>> call, Throwable t) {
                progressdialog.dismiss();
                Toast.makeText(thi_tracnghiem.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sendResult() {
        //chưa xử lý và truyền time
        Result result = new Result(TruyenDuLieu.trEmail_dnhap,TruyenDuLieu.trMaDe,"exam","",MaBoDe,adslist);
        Call<Result> call = retrofitInterface.saveResult(result);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.e("send result","ok");
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("send result","fail");
                Toast.makeText(thi_tracnghiem.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void bamTracNghiem (List<CauHoiTracNghiem> adslist) {
        clickOTracNghiem(adslist,ar_textview[1], ar_textview, ar_string[5]);
        clickOTracNghiem(adslist,ar_textview[2], ar_textview, ar_string[5]);
        clickOTracNghiem(adslist,ar_textview[3], ar_textview, ar_string[5]);
        clickOTracNghiem(adslist,ar_textview[4], ar_textview, ar_string[5]);
    }

    private void clickOTracNghiem(List<CauHoiTracNghiem> list,TextView a, TextView tv[], String b) {
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(Cauhoihientai).getCauhoidachon()!=null) {
                    for (int i=1;i<5;i++) {
                        tv[i].setBackgroundResource(R.drawable.bg_otracnghiem);
                    }
                }
                ar_string[6] = a.getText().toString();
                list.get(Cauhoihientai).setCauhoidachon(ar_string[6]);

                a.setBackgroundResource(R.drawable.bg_otracnghiem_cam);
                socaudalam++;
                socauchualam--;
                chondapan[Cauhoihientai] = "dachon";

                if (a.getText().toString().equals(b)) {
                    Diem++;
                }
            }
        });
        Log.e("Diem", String.valueOf(Diem));
    }

    private void gan_gia_tri(List<CauHoiTracNghiem> a, String arg[], TextView tv[]) {
        arg[0] = a.get(Cauhoihientai).getQuestion();
        arg[1] = a.get(Cauhoihientai).getA();
        arg[2] = a.get(Cauhoihientai).getB();
        arg[3] = a.get(Cauhoihientai).getC();
        arg[4] = a.get(Cauhoihientai).getD();
        arg[5] = a.get(Cauhoihientai).getAnw();

        //xóa background ô đáp án khi đổi câu hỏi.
        for (int i = 1; i < 5; i++) {
            tv[i].setBackgroundResource(R.drawable.bg_otracnghiem);
        }
        if (a.get(Cauhoihientai).getCauhoidachon() == null) {
            arg[6] = "";
        } else {
            arg[6] = a.get(Cauhoihientai).getCauhoidachon();
            for (int i = 1; i < 5; i++) {
                if (arg[i].equals(arg[6])) {
                    tv[i].setBackgroundResource(R.drawable.bg_otracnghiem_cam);
                }
            }
        }

        tv[0].setText(arg[0]);
        tv[1].setText(arg[1]);
        tv[2].setText(arg[2]);
        tv[3].setText(arg[3]);
        tv[4].setText(arg[4]);

        //hiển thị số câu
        socau.setText(String.valueOf(Cauhoihientai+1));

    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_bottomsheet_xemnhanh_thi);

        ar_tv_bottom[0] = dialog.findViewById(R.id.tv_cau1_xemnhanhthi);
        ar_tv_bottom[1] = dialog.findViewById(R.id.tv_cau2_xemnhanhthi);
        ar_tv_bottom[2] = dialog.findViewById(R.id.tv_cau3_xemnhanhthi);
        ar_tv_bottom[3] = dialog.findViewById(R.id.tv_cau4_xemnhanhthi);
        ar_tv_bottom[4] = dialog.findViewById(R.id.tv_cau5_xemnhanhthi);
        ar_tv_bottom[5] = dialog.findViewById(R.id.tv_cau6_xemnhanhthi);
        ar_tv_bottom[6] = dialog.findViewById(R.id.tv_cau7_xemnhanhthi);
        ar_tv_bottom[7] = dialog.findViewById(R.id.tv_cau8_xemnhanhthi);
        ar_tv_bottom[8] = dialog.findViewById(R.id.tv_cau9_xemnhanhthi);
        ar_tv_bottom[9] = dialog.findViewById(R.id.tv_cau10_xemnhanhthi);
        ar_tv_bottom[10] = dialog.findViewById(R.id.tv_cau11_xemnhanhthi);
        ar_tv_bottom[11] = dialog.findViewById(R.id.tv_cau12_xemnhanhthi);
        ar_tv_bottom[12] = dialog.findViewById(R.id.tv_cau13_xemnhanhthi);
        ar_tv_bottom[13] = dialog.findViewById(R.id.tv_cau14_xemnhanhthi);
        ar_tv_bottom[14] = dialog.findViewById(R.id.tv_cau15_xemnhanhthi);
        ar_tv_bottom[15] = dialog.findViewById(R.id.tv_cau16_xemnhanhthi);
        ar_tv_bottom[16] = dialog.findViewById(R.id.tv_cau17_xemnhanhthi);
        ar_tv_bottom[17] = dialog.findViewById(R.id.tv_cau18_xemnhanhthi);
        ar_tv_bottom[18] = dialog.findViewById(R.id.tv_cau19_xemnhanhthi);
        ar_tv_bottom[19] = dialog.findViewById(R.id.tv_cau20_xemnhanhthi);
        ar_tv_bottom[20] = dialog.findViewById(R.id.tv_cau21_xemnhanhthi);
        ar_tv_bottom[21] = dialog.findViewById(R.id.tv_cau22_xemnhanhthi);
        ar_tv_bottom[22] = dialog.findViewById(R.id.tv_cau23_xemnhanhthi);
        ar_tv_bottom[23] = dialog.findViewById(R.id.tv_cau24_xemnhanhthi);
        ar_tv_bottom[24] = dialog.findViewById(R.id.tv_cau25_xemnhanhthi);
        ar_tv_bottom[25] = dialog.findViewById(R.id.tv_cau26_xemnhanhthi);
        ar_tv_bottom[26] = dialog.findViewById(R.id.tv_cau27_xemnhanhthi);
        ar_tv_bottom[27] = dialog.findViewById(R.id.tv_cau28_xemnhanhthi);
        ar_tv_bottom[28] = dialog.findViewById(R.id.tv_cau29_xemnhanhthi);
        ar_tv_bottom[29] = dialog.findViewById(R.id.tv_cau30_xemnhanhthi);
        ar_tv_bottom[30] = dialog.findViewById(R.id.tv_cau31_xemnhanhthi);
        ar_tv_bottom[31] = dialog.findViewById(R.id.tv_cau32_xemnhanhthi);
        ar_tv_bottom[32] = dialog.findViewById(R.id.tv_cau33_xemnhanhthi);
        ar_tv_bottom[33] = dialog.findViewById(R.id.tv_cau34_xemnhanhthi);
        ar_tv_bottom[34] = dialog.findViewById(R.id.tv_cau35_xemnhanhthi);
        ar_tv_bottom[35] = dialog.findViewById(R.id.tv_cau36_xemnhanhthi);
        ar_tv_bottom[36] = dialog.findViewById(R.id.tv_cau37_xemnhanhthi);
        ar_tv_bottom[37] = dialog.findViewById(R.id.tv_cau38_xemnhanhthi);
        ar_tv_bottom[38] = dialog.findViewById(R.id.tv_cau39_xemnhanhthi);
        ar_tv_bottom[39] = dialog.findViewById(R.id.tv_cau40_xemnhanhthi);
        TextView txt_socaudalam = dialog.findViewById(R.id.txt_thi_xemnhanh_dalam);
        TextView txt_socauchualam = dialog.findViewById(R.id.txt_thi_xemnhanh_chualam);

        txt_socauchualam.setText(String.format("%02d",socauchualam));
        txt_socaudalam.setText(String.format("%02d",socaudalam));

        for (int i = 0; i < chondapan.length; i++) {
            if (chondapan[i] == "dachon") {
                ar_tv_bottom[i].setBackgroundResource(R.drawable.bg_xemnhanh_cam);
            }
        }

        bamtextview(ar_tv_bottom[0],0,dialog);
        bamtextview(ar_tv_bottom[1],1,dialog);
        bamtextview(ar_tv_bottom[2],2,dialog);
        bamtextview(ar_tv_bottom[3],3,dialog);
        bamtextview(ar_tv_bottom[4],4,dialog);
        bamtextview(ar_tv_bottom[5],5,dialog);
        bamtextview(ar_tv_bottom[6],6,dialog);
        bamtextview(ar_tv_bottom[7],7,dialog);
        bamtextview(ar_tv_bottom[8],8,dialog);
        bamtextview(ar_tv_bottom[9],9,dialog);
        bamtextview(ar_tv_bottom[10],10,dialog);
        bamtextview(ar_tv_bottom[11],11,dialog);
        bamtextview(ar_tv_bottom[12],12,dialog);
        bamtextview(ar_tv_bottom[13],13,dialog);
        bamtextview(ar_tv_bottom[14],14,dialog);
        bamtextview(ar_tv_bottom[15],15,dialog);
        bamtextview(ar_tv_bottom[16],16,dialog);
        bamtextview(ar_tv_bottom[17],17,dialog);
        bamtextview(ar_tv_bottom[18],18,dialog);
        bamtextview(ar_tv_bottom[19],19,dialog);
        bamtextview(ar_tv_bottom[20],20,dialog);
        bamtextview(ar_tv_bottom[21],21,dialog);
        bamtextview(ar_tv_bottom[22],22,dialog);
        bamtextview(ar_tv_bottom[23],23,dialog);
        bamtextview(ar_tv_bottom[24],24,dialog);
        bamtextview(ar_tv_bottom[25],25,dialog);
        bamtextview(ar_tv_bottom[26],26,dialog);
        bamtextview(ar_tv_bottom[27],27,dialog);
        bamtextview(ar_tv_bottom[28],28,dialog);
        bamtextview(ar_tv_bottom[29],29,dialog);
        bamtextview(ar_tv_bottom[30],30,dialog);
        bamtextview(ar_tv_bottom[31],31,dialog);
        bamtextview(ar_tv_bottom[32],32,dialog);
        bamtextview(ar_tv_bottom[33],33,dialog);
        bamtextview(ar_tv_bottom[34],34,dialog);
        bamtextview(ar_tv_bottom[35],35,dialog);
        bamtextview(ar_tv_bottom[36],36,dialog);
        bamtextview(ar_tv_bottom[37],37,dialog);
        bamtextview(ar_tv_bottom[38],38,dialog);
        bamtextview(ar_tv_bottom[39],39,dialog);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    private void bamtextview(TextView tv, int i,Dialog dialog) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                Intent intent = new Intent();
                intent.setAction("Trắc nghiệm thi");
                intent.putExtra("id",i);
                LocalBroadcastManager.getInstance(thi_tracnghiem.this).sendBroadcast(intent);
            }
        });
    }

}