package com.example.dacn.cauhoi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacn.Api.RetrofitInterface;
import com.example.dacn.R;
import com.example.dacn.dangnhap.dang_ky;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ontap_tracnghiem extends AppCompatActivity {

    TextView cauhoi,cautraloia,cautraloib,cautraloic,cautraloid;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.1.5:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ontap_tracnghiem);

        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        cauhoi = findViewById(R.id.inputon_cauhoi);
        cautraloia = findViewById(R.id.oncautraloia);
        cautraloib = findViewById(R.id.oncautraloib);
        cautraloic = findViewById(R.id.oncautraloic);
        cautraloid = findViewById(R.id.oncautraloid);

        Call<List<CauHoiTracNghiem>> call = retrofitInterface.getCauHoiTracNghiem();

        call.enqueue(new Callback<List<CauHoiTracNghiem>>() {
            @Override
            public void onResponse(Call<List<CauHoiTracNghiem>> call, Response<List<CauHoiTracNghiem>> response) {
                List<CauHoiTracNghiem> adslist = response.body();

                String cauHoi = adslist.get(0).getQuestion();
                String cauA = adslist.get(0).getA();
                String cauB = adslist.get(0).getB();
                String cauC = adslist.get(0).getC();
                String cauD= adslist.get(0).getD();
                String anw = adslist.get(0).getAnw();

                cauhoi.setText(cauHoi);
                cautraloia.setText(cauA);
                cautraloib.setText(cauB);
                cautraloic.setText(cauC);
                cautraloid.setText(cauD);

                cautraloia.setOnClickListener(new View.OnClickListener() {
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
                });

            }

            @Override
            public void onFailure(Call<List<CauHoiTracNghiem>> call, Throwable t) {
                Toast.makeText(ontap_tracnghiem.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
