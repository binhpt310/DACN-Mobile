package com.example.dacn.cauhoi;
import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.dangnhap.dang_ky;
import com.example.dacn.hoanthanhbai.hoanthanhbaithi;
import com.example.dacn.popup.LoadingDialog;
import com.example.dacn.popup.popup_hoan_thanh_thi_thu;
import com.example.dacn.popup.popup_tro_ve;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ontap_tracnghiem extends AppCompatActivity {

    TextView xemnhanh, socau, txt_toolbar;
    TextView[] ar_textview = new TextView[5];
    String[] ar_string = new String[7]; //0 cauhoi, 1-4 dapan, 5 dapandung, 6 cauchon
    public String[] listdungsai = new String[20];

    ImageView btn_back, img_toi, img_lui, btn_done;
    ImageView[] arr_img_progress = new ImageView[20];
    public int Cauhoihientai,socauchualam=20,socaudung=0,socausai=0;

    TextView[] ar_tv_bottom = new TextView[20];
    String MaBoDe;

    ProgressDialog progressdialog;

    List<CauHoiTracNghiem> adslist = new ArrayList<CauHoiTracNghiem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ontap_tracnghiem);

        progressdialog = new ProgressDialog(ontap_tracnghiem.this);
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
        String text = "Ôn tập " + TruyenDuLieu.trTenMon + " - đề số " + MaBoDe;
        txt_toolbar.setText(text);

        //20 giá trị rỗng cho mảng đúng sai
        for (int i = 0; i < listdungsai.length; i++) {
            listdungsai[i] = "";
        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ontap_tracnghiem.this, popup_tro_ve.class);
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

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendResult();
                Intent intent = new Intent(getApplicationContext(), popup_hoan_thanh_thi_thu.class);
                startActivity(intent);
            }
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction("Trắc nghiệm ôn");
        BroadcastReceiver mRefreshReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("Trắc nghiệm ôn")) {
                    Cauhoihientai = intent.getIntExtra("id",-1);
                    gan_gia_tri(adslist,ar_string,ar_textview,arr_img_progress[Cauhoihientai],ar_tv_bottom[Cauhoihientai]);
                    if (adslist.get(Cauhoihientai).getCauhoidachon()!=null) {
                        for (int i=1;i<5;i++) {
                            ar_textview[i].setOnClickListener(null);
                        }
                    } else {
                        bamTracNghiem(adslist);
                    }
                }
            }
        };
        LocalBroadcastManager.getInstance(ontap_tracnghiem.this).registerReceiver(mRefreshReceiver, filter);

        img_toi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cauhoihientai++;
                if (Cauhoihientai < (adslist.size())){
                    gan_gia_tri(adslist,ar_string,ar_textview,arr_img_progress[Cauhoihientai],ar_tv_bottom[Cauhoihientai]);
                    img_toi.setVisibility(View.VISIBLE);
                    img_lui.setVisibility(View.VISIBLE);
                    if (Cauhoihientai == (adslist.size()-1)) {img_toi.setVisibility(View.INVISIBLE);}
                    else if (Cauhoihientai == 0) {img_lui.setVisibility(View.INVISIBLE);}
                }
                if (adslist.get(Cauhoihientai).getCauhoidachon()!=null) {
                    for (int i=1;i<5;i++) {
                        ar_textview[i].setOnClickListener(null);
                    }
                } else {
                    bamTracNghiem(adslist);
                }
            }
        });

        img_lui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cauhoihientai--;
                if (Cauhoihientai < (adslist.size())){
                    gan_gia_tri(adslist,ar_string,ar_textview,arr_img_progress[Cauhoihientai],ar_tv_bottom[Cauhoihientai]);
                    img_toi.setVisibility(View.VISIBLE);
                    img_lui.setVisibility(View.VISIBLE);
                    if (Cauhoihientai == (adslist.size()-1)) {img_toi.setVisibility(View.INVISIBLE);}
                    else if (Cauhoihientai == 0) {img_lui.setVisibility(View.INVISIBLE);}
                }
                if (adslist.get(Cauhoihientai).getCauhoidachon()!=null) {
                    for (int i=1;i<5;i++) {
                        ar_textview[i].setOnClickListener(null);
                    }
                } else {
                    bamTracNghiem(adslist);
                }
            }
        });
    }

    private void sendResult() {
        Result result = new Result(TruyenDuLieu.trEmail_dnhap,TruyenDuLieu.trMaDe,"review","",MaBoDe,adslist);
        Call<Result> call = retrofitInterface.saveResult(result);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.e("send result","ok");
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e("send result","fail");
                Toast.makeText(ontap_tracnghiem.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void callApi () {
        progressdialog.show();
        Cauhoihientai = TruyenDuLieu.trCauhoihientai;

        HashMap<String, String> map = new HashMap<>();
        map.put("sub", TruyenDuLieu.trMaDe);
        map.put("Code", MaBoDe);
        Call<List<CauHoiTracNghiem>> call = retrofitInterface.getCauHoiTracNghiem(map);
        call.enqueue(new Callback<List<CauHoiTracNghiem>>() {
            @Override
            public void onResponse(Call<List<CauHoiTracNghiem>> call, Response<List<CauHoiTracNghiem>> response) {
                adslist = response.body();
                progressdialog.dismiss();

                gan_gia_tri(adslist,ar_string,ar_textview,arr_img_progress[Cauhoihientai],ar_tv_bottom[Cauhoihientai]);
                bamTracNghiem(adslist);
            }

            @Override
            public void onFailure(Call<List<CauHoiTracNghiem>> call, Throwable t) {
                progressdialog.dismiss();
                Toast.makeText(ontap_tracnghiem.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void bamTracNghiem (List<CauHoiTracNghiem> adslist) {
        clickOTracNghiem(adslist,ar_textview[1], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
        clickOTracNghiem(adslist,ar_textview[2], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
        clickOTracNghiem(adslist,ar_textview[3], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
        clickOTracNghiem(adslist,ar_textview[4], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai]);
    }

    private void clickOTracNghiem(List<CauHoiTracNghiem> list,TextView a, TextView tv[], String b, ImageView imageView) {
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ar_string[6] = a.getText().toString();
                list.get(Cauhoihientai).setCauhoidachon(ar_string[6]);
                boolean result = a.getText().toString().equals(b);
                if (result) {
                    a.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                    imageView.setImageResource(R.drawable.bg_line_xanh);
                    socaudung++;
                    listdungsai[Cauhoihientai] = "dung";
                    list.get(Cauhoihientai).setDungsai("dung");
                }

                else {
                    a.setBackgroundResource(R.drawable.bg_otracnghiem_do);
                    imageView.setImageResource(R.drawable.bg_line_do);
                    socausai++;
                    listdungsai[Cauhoihientai] = "sai";
                    list.get(Cauhoihientai).setDungsai("sai");
                    for (int i=1;i<5;i++) {
                        if (tv[i].getText().toString().equals(b)) {
                            tv[i].setBackgroundResource(R.drawable.bg_otracnghiem_xanh);

                        }
                    }
                }
                for (int i=1;i<5;i++) {
                    tv[i].setOnClickListener(null);
                }
                socauchualam--;
            }
        });
    }

    private void gan_gia_tri(List<CauHoiTracNghiem> a, String arg[], TextView tv[], ImageView imageView, TextView xemnhanh) {
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
            //đang ở câu nào thì set trắng ngay img đó
            imageView.setImageResource(R.drawable.bg_line_trang);
        } else {
            arg[6] = a.get(Cauhoihientai).getCauhoidachon();
            for (int i = 1; i < 5; i++) {
                if (arg[i].equals(arg[6])) {
                    if (arg[i].equals(arg[5])) {
                        tv[i].setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                    } else {
                        tv[i].setBackgroundResource(R.drawable.bg_otracnghiem_do);
                        for (int j=1;j<5;j++) {
                            if (arg[j].equals(arg[5])) {
                                tv[j].setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                            }
                        }
                    }
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

    private void khaibao() {
        ar_textview[0] = findViewById(R.id.inputon_cauhoi);
        ar_textview[1] = findViewById(R.id.oncautraloia);
        ar_textview[2] = findViewById(R.id.oncautraloib);
        ar_textview[3] = findViewById(R.id.oncautraloic);
        ar_textview[4] = findViewById(R.id.oncautraloid);
        xemnhanh = findViewById(R.id.btn_on_xemnhanh);
        socau = findViewById(R.id.txt_on_socauhoi);
        btn_back = findViewById(R.id.img_back_ontap);
        btn_done = findViewById(R.id.img_out_ontap);
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

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_bottomsheet_xemnhanh_on);

        ar_tv_bottom[0] = dialog.findViewById(R.id.tv_cau1_xemnhanhon);
        ar_tv_bottom[1] = dialog.findViewById(R.id.tv_cau2_xemnhanhon);
        ar_tv_bottom[2] = dialog.findViewById(R.id.tv_cau3_xemnhanhon);
        ar_tv_bottom[3] = dialog.findViewById(R.id.tv_cau4_xemnhanhon);
        ar_tv_bottom[4] = dialog.findViewById(R.id.tv_cau5_xemnhanhon);
        ar_tv_bottom[5] = dialog.findViewById(R.id.tv_cau6_xemnhanhon);
        ar_tv_bottom[6] = dialog.findViewById(R.id.tv_cau7_xemnhanhon);
        ar_tv_bottom[7] = dialog.findViewById(R.id.tv_cau8_xemnhanhon);
        ar_tv_bottom[8] = dialog.findViewById(R.id.tv_cau9_xemnhanhon);
        ar_tv_bottom[9] = dialog.findViewById(R.id.tv_cau10_xemnhanhon);
        ar_tv_bottom[10] = dialog.findViewById(R.id.tv_cau11_xemnhanhon);
        ar_tv_bottom[11] = dialog.findViewById(R.id.tv_cau12_xemnhanhon);
        ar_tv_bottom[12] = dialog.findViewById(R.id.tv_cau13_xemnhanhon);
        ar_tv_bottom[13] = dialog.findViewById(R.id.tv_cau14_xemnhanhon);
        ar_tv_bottom[14] = dialog.findViewById(R.id.tv_cau15_xemnhanhon);
        ar_tv_bottom[15] = dialog.findViewById(R.id.tv_cau16_xemnhanhon);
        ar_tv_bottom[16] = dialog.findViewById(R.id.tv_cau17_xemnhanhon);
        ar_tv_bottom[17] = dialog.findViewById(R.id.tv_cau18_xemnhanhon);
        ar_tv_bottom[18] = dialog.findViewById(R.id.tv_cau19_xemnhanhon);
        ar_tv_bottom[19] = dialog.findViewById(R.id.tv_cau20_xemnhanhon);

        TextView txt_socaudung = dialog.findViewById(R.id.txt_on_xemnhanh_dung);
        TextView txt_socausai = dialog.findViewById(R.id.txt_on_xemnhanh_sai);
        TextView txt_socauchualam = dialog.findViewById(R.id.txt_on_xemnhanh_chualam);

        txt_socauchualam.setText(String.format("%02d",socauchualam));
        txt_socaudung.setText(String.format("%02d",socaudung));
        txt_socausai.setText(String.format("%02d",socausai));

        for (int i = 0; i < listdungsai.length; i++) {
            if (listdungsai[i] == "dung") {
                ar_tv_bottom[i].setBackgroundResource(R.drawable.bg_xemnhanh_xanh);
            } else if (listdungsai[i] == "sai"){
                ar_tv_bottom[i].setBackgroundResource(R.drawable.bg_xemnhanh_do);
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
                intent.setAction("Trắc nghiệm ôn");
                intent.putExtra("id",i);
                LocalBroadcastManager.getInstance(ontap_tracnghiem.this).sendBroadcast(intent);
            }
        });
    }
}
