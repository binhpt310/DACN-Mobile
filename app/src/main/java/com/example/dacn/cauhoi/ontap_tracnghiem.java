package com.example.dacn.cauhoi;
import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.hoanthanhbai.hoanthanhbaithi;
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

    TextView xemnhanh, socau, txt_toolbar,txt_socaudung,txt_socausai,txt_socauchualam;
    TextView[] ar_textview = new TextView[5];
    String[] ar_string = new String[7];
    ImageView btn_back, img_toi, img_lui;
    ImageView[] arr_img_progress = new ImageView[20];
    public int Cauhoihientai = 0,diem = 0,socauchualam=20,socaudung=0,socausai=0;

    LinearLayout layoutBottom;
    BottomSheetBehavior bottomSheetBehavior;
    TextView[] ar_tv_bottom = new TextView[20];

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


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ontap_tracnghiem.this, bo_de_thi.class);
                startActivity(intent);
            }
        });

        xemnhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(ontap_tracnghiem.this, hoanthanhbaithi.class);
                startActivity(intent);*/
                //Log.e("diem", String.valueOf(diem));

                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
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

                gan_gia_tri(adslist,ar_string,ar_textview,arr_img_progress[Cauhoihientai],ar_tv_bottom[Cauhoihientai]);
                bamtracnghiem2();

                img_toi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cauhoihientai++;
                        if (Cauhoihientai < (adslist.size())){
                            gan_gia_tri(adslist,ar_string,ar_textview,arr_img_progress[Cauhoihientai],ar_tv_bottom[Cauhoihientai]);
                            if (Cauhoihientai == (adslist.size()-1)) {img_toi.setVisibility(View.INVISIBLE);}
                            if (Cauhoihientai > 0) {img_lui.setVisibility(View.VISIBLE);}
                        }
                        bamtracnghiem2();
                        //Log.e("caudachon",ar_string[6]);
                    }
                });

                img_lui.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cauhoihientai--;
                        if (Cauhoihientai < (adslist.size())){
                            gan_gia_tri(adslist,ar_string,ar_textview,arr_img_progress[Cauhoihientai],ar_tv_bottom[Cauhoihientai]);
                            if (Cauhoihientai == 0) {img_lui.setVisibility(View.INVISIBLE);}
                        }
                        bamtracnghiem2();
                    }
                });

            }

            @Override
            public void onFailure(Call<List<CauHoiTracNghiem>> call, Throwable t) {
                Toast.makeText(ontap_tracnghiem.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void bamtracnghiem2() {
        bamtracnghiem(ar_textview[1], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai],ar_tv_bottom[Cauhoihientai]);
        bamtracnghiem(ar_textview[2], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai],ar_tv_bottom[Cauhoihientai]);
        bamtracnghiem(ar_textview[3], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai],ar_tv_bottom[Cauhoihientai]);
        bamtracnghiem(ar_textview[4], ar_textview, ar_string[5],arr_img_progress[Cauhoihientai],ar_tv_bottom[Cauhoihientai]);
    }

    private void bamtracnghiem(TextView a, TextView tv[], String b, ImageView imageView, TextView textView) {
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cauchon = a.getText().toString();

                boolean result = a.getText().toString().equals(b);
                if (result) {
                    a.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                    imageView.setImageResource(R.drawable.bg_line_xanh);
                    socaudung++;
                    txt_socaudung.setText(String.valueOf(socaudung));
                    textView.setBackgroundResource(R.drawable.bg_xemnhanh_xanh);

                }

                else {
                    a.setBackgroundResource(R.drawable.bg_otracnghiem_do);
                    imageView.setImageResource(R.drawable.bg_line_do);
                    textView.setBackgroundResource(R.drawable.bg_xemnhanh_do);
                    socausai++;
                    txt_socausai.setText(String.valueOf(socausai));
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
                txt_socauchualam.setText(String.valueOf(socauchualam));
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

        tv[0].setText(arg[0]);
        tv[1].setText(arg[1]);
        tv[2].setText(arg[2]);
        tv[3].setText(arg[3]);
        tv[4].setText(arg[4]);

        //hiển thị số câu
        socau.setText(String.valueOf(Cauhoihientai+1));

        //đang ở câu nào thì set trắng ngay img đó
        imageView.setImageResource(R.drawable.bg_line_trang);

        //xóa background ô đáp án khi đổi câu hỏi.
        for (int i = 1; i < 5; i++) {
            tv[i].setBackgroundResource(R.drawable.bg_otracnghiem);
        }

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

        ar_tv_bottom[0] = findViewById(R.id.tv_cau1_xemnhanhon);
        ar_tv_bottom[1] = findViewById(R.id.tv_cau2_xemnhanhon);
        ar_tv_bottom[2] = findViewById(R.id.tv_cau3_xemnhanhon);
        ar_tv_bottom[3] = findViewById(R.id.tv_cau4_xemnhanhon);
        ar_tv_bottom[4] = findViewById(R.id.tv_cau5_xemnhanhon);
        ar_tv_bottom[5] = findViewById(R.id.tv_cau6_xemnhanhon);
        ar_tv_bottom[6] = findViewById(R.id.tv_cau7_xemnhanhon);
        ar_tv_bottom[7] = findViewById(R.id.tv_cau8_xemnhanhon);
        ar_tv_bottom[8] = findViewById(R.id.tv_cau9_xemnhanhon);
        ar_tv_bottom[9] = findViewById(R.id.tv_cau10_xemnhanhon);
        ar_tv_bottom[10] = findViewById(R.id.tv_cau11_xemnhanhon);
        ar_tv_bottom[11] = findViewById(R.id.tv_cau12_xemnhanhon);
        ar_tv_bottom[12] = findViewById(R.id.tv_cau13_xemnhanhon);
        ar_tv_bottom[13] = findViewById(R.id.tv_cau14_xemnhanhon);
        ar_tv_bottom[14] = findViewById(R.id.tv_cau15_xemnhanhon);
        ar_tv_bottom[15] = findViewById(R.id.tv_cau16_xemnhanhon);
        ar_tv_bottom[16] = findViewById(R.id.tv_cau17_xemnhanhon);
        ar_tv_bottom[17] = findViewById(R.id.tv_cau18_xemnhanhon);
        ar_tv_bottom[18] = findViewById(R.id.tv_cau19_xemnhanhon);
        ar_tv_bottom[19] = findViewById(R.id.tv_cau20_xemnhanhon);

        img_toi = findViewById(R.id.img_toi);
        img_lui = findViewById(R.id.img_lui);

        layoutBottom = findViewById(R.id.bottom_sheet_layout_xemnhanhon);
        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottom);

        txt_socaudung = findViewById(R.id.txt_on_xemnhanh_dung);
        txt_socausai = findViewById(R.id.txt_on_xemnhanh_sai);
        txt_socauchualam = findViewById(R.id.txt_on_xemnhanh_chualam);
    }
}
