package com.example.dacn.cauhoi;
import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacn.RetrofitInterface;
import com.example.dacn.R;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ontap_tracnghiem extends AppCompatActivity {

    TextView xemnhanh, socau;
    TextView[] ar_textview = new TextView[5];

    String[] ar_string = new String[6];

    public int Cauhoihientai = 0;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ontap_tracnghiem);

        khaibao();

        Call<List<CauHoiTracNghiem>> call = retrofitInterface.getCauHoiTracNghiem();

        call.enqueue(new Callback<List<CauHoiTracNghiem>>() {
            @Override
            public void onResponse(Call<List<CauHoiTracNghiem>> call, Response<List<CauHoiTracNghiem>> response) {
                List<CauHoiTracNghiem> adslist = response.body();

                gan_gia_tri(adslist,ar_string,ar_textview);

                xemnhanh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Cauhoihientai++;
                        Log.e("ee", String.valueOf(Cauhoihientai));

                        if (Cauhoihientai < (adslist.size())){
                            gan_gia_tri(adslist,ar_string,ar_textview);
                        } else {
                            xemnhanh.setOnClickListener(null);
                        }
                    }
                });

                /*for (int i=1;i<5;i++) {
                    ar_textview[].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ktradungsai(ar_textview[],ar_string[]);
                        }
                    });
                }*/

                /*cautraloia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (cauA.equals(anw)) {
                            cautraloia.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                        } else {
                            cautraloia.setBackgroundResource(R.drawable.bg_otracnghiem_do);
                            if (cauB.equals(anw)) {
                                cautraloib.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                            } else if (cauC.equals(anw)) {
                                cautraloic.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                            } else if (cauD.equals(anw)) {
                                cautraloid.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                            }
                        }
                        cautraloib.setOnClickListener(null);
                        cautraloic.setOnClickListener(null);
                        cautraloid.setOnClickListener(null);
                    }
                });

                cautraloib.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (cauB.equals(anw)) {
                            cautraloib.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                        } else {
                            cautraloib.setBackgroundResource(R.drawable.bg_otracnghiem_do);
                            if (cauA.equals(anw)) {
                                cautraloia.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                            } else if (cauC.equals(anw)) {
                                cautraloic.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                            } else if (cauD.equals(anw)) {
                                cautraloid.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                            }
                        }
                        cautraloia.setOnClickListener(null);
                        cautraloic.setOnClickListener(null);
                        cautraloid.setOnClickListener(null);
                    }
                });

                cautraloic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (cauC.equals(anw)) {
                            cautraloic.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                        } else {
                            cautraloic.setBackgroundResource(R.drawable.bg_otracnghiem_do);
                            if (cauA.equals(anw)) {
                                cautraloia.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                            } else if (cauB.equals(anw)) {
                                cautraloib.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                            } else if (cauD.equals(anw)) {
                                cautraloid.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                            }
                        }
                        cautraloia.setOnClickListener(null);
                        cautraloib.setOnClickListener(null);
                        cautraloid.setOnClickListener(null);
                    }
                });

                cautraloid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (cauD.equals(anw)) {
                            cautraloid.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                        } else {
                            cautraloid.setBackgroundResource(R.drawable.bg_otracnghiem_do);
                            if (cauA.equals(anw)) {
                                cautraloia.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                            } else if (cauB.equals(anw)) {
                                cautraloib.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                            } else if (cauC.equals(anw)) {
                                cautraloic.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
                            }
                        }
                        cautraloia.setOnClickListener(null);
                        cautraloib.setOnClickListener(null);
                        cautraloic.setOnClickListener(null);
                    }
                });*/

            }

            @Override
            public void onFailure(Call<List<CauHoiTracNghiem>> call, Throwable t) {
                Toast.makeText(ontap_tracnghiem.this, t.getMessage(),Toast.LENGTH_LONG).show();
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
    }

    private void gan_gia_tri(List<CauHoiTracNghiem> a, String arg[], TextView tv[]) {
        arg[0] = a.get(Cauhoihientai).getQuestion();
        arg[1] = a.get(Cauhoihientai).getA();
        arg[2] = a.get(Cauhoihientai).getB();
        arg[3] = a.get(Cauhoihientai).getC();
        arg[4] = a.get(Cauhoihientai).getD();
        arg[5] = a.get(Cauhoihientai).getAnw();

        tv[0].setText(arg[0]);

        //random trùng
        for (int i=1;i<5;i++) {
            int value = random.nextInt((5 - 1) + 1) + 1;
            tv[i].setText(arg[value]);
        }

        /*tv[1].setText(arg[1]);
        tv[2].setText(arg[2]);
        tv[3].setText(arg[3]);
        tv[4].setText(arg[4]);*/

        socau.setText(String.valueOf(Cauhoihientai+1));
    }


    /*private void ktradungsai(TextView a[], String arg[]) {
        for (int i=1; i<4; i++) {
            int j=i;
            if (arg[i].equals(arg[5])) {
                a[j].setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
            }
            }
        }*/
        /*if (cauA.equals(arg[5])) {
            a.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
        } else {
            a.setBackgroundResource(R.drawable.bg_otracnghiem_do);
            if (cauB.equals(arg[5])) {
                b.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
            } else if (cauC.equals(arg[5])) {
                c.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
            } else if (cauD.equals(arg[5])) {
                d.setBackgroundResource(R.drawable.bg_otracnghiem_xanh);
            }
        }
        b.setOnClickListener(null);
        c.setOnClickListener(null);
        d.setOnClickListener(null);*/
    //}

}
