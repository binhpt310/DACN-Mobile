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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ontap_tracnghiem extends AppCompatActivity {

    TextView cauhoi, cautraloia, cautraloib, cautraloic, cautraloid, xemnhanh;

    public int Cauhoihientai = 0;

    String cauHoi;
    String cauA;
    String cauB;
    String cauC;
    String cauD;
    String anw ;

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

                cauHoi = adslist.get(Cauhoihientai).getQuestion();
                cauA = adslist.get(Cauhoihientai).getA();
                cauB = adslist.get(Cauhoihientai).getB();
                cauC = adslist.get(Cauhoihientai).getC();
                cauD = adslist.get(Cauhoihientai).getD();
                anw = adslist.get(Cauhoihientai).getAnw();

                cauhoi.setText(cauHoi);
                cautraloia.setText(cauA);
                cautraloib.setText(cauB);
                cautraloic.setText(cauC);
                cautraloid.setText(cauD);

                xemnhanh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Cauhoihientai++;

                        if (Cauhoihientai < (adslist.size())){
                            cauHoi = adslist.get(Cauhoihientai).getQuestion();
                            cauA = adslist.get(Cauhoihientai).getA();
                            cauB = adslist.get(Cauhoihientai).getB();
                            cauC = adslist.get(Cauhoihientai).getC();
                            cauD = adslist.get(Cauhoihientai).getD();
                            anw = adslist.get(Cauhoihientai).getAnw();
                        } else {
                            xemnhanh.setOnClickListener(null);
                        }

                        cauhoi.setText(cauHoi);
                        cautraloia.setText(cauA);
                        cautraloib.setText(cauB);
                        cautraloic.setText(cauC);
                        cautraloid.setText(cauD);
                    }
                });

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
        cauhoi = findViewById(R.id.inputon_cauhoi);
        cautraloia = findViewById(R.id.oncautraloia);
        cautraloib = findViewById(R.id.oncautraloib);
        cautraloic = findViewById(R.id.oncautraloic);
        cautraloid = findViewById(R.id.oncautraloid);
        xemnhanh = findViewById(R.id.btn_on_xemnhanh);
    }

}
