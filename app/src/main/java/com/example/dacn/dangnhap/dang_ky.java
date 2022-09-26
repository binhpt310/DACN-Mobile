package com.example.dacn.dangnhap;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacn.RetrofitInterface;
import com.example.dacn.R;
import com.example.dacn.trangchu2;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class dang_ky extends AppCompatActivity{

    boolean temp = false;
    /*String chckppw ="i";
    String passwrd ="i";*/

    Button btn_dangky;
    TextView txt_dangnhap;
    TextInputEditText email, tenngdung, matkhau, nhaplaimk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        btn_dangky = findViewById(R.id.btn_dangky);
        txt_dangnhap = findViewById(R.id.dangnhapotrangdangky);

        email = findViewById(R.id.txtdki_email);
        tenngdung = findViewById(R.id.txtdki_tenngdung);
        matkhau = findViewById(R.id.txtdki_matkhau);
        nhaplaimk = findViewById(R.id.txtdki_nhaplaimk);

        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatePassword() && validateEmail()) {
                    HashMap<String, String> map = new HashMap<>();

                    map.put("email", email.getText().toString());
                    map.put("tenngdung", tenngdung.getText().toString());
                    map.put("matkhau", matkhau.getText().toString());

                    Call<Void> call = retrofitInterface.executeSignup(map);
                    call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Intent intent = new Intent(dang_ky.this, trangchu2.class);
                            startActivity(intent);
                            finish();
                        } else if (response.code() == 201) {
                            Toast.makeText(dang_ky.this, "Email đã tồn tại", Toast.LENGTH_LONG).show();
                        } else if (response.code() == 404) {
                            Toast.makeText(dang_ky.this, "Lỗi", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(dang_ky.this, t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
                }
            }
        });

        txt_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dang_ky.this, dang_nhap.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validateEmail() {
        String emailInput = email.getText().toString().trim();
        if (emailInput.isEmpty()) {
            Toast.makeText(this, "Email đang để trống", Toast.LENGTH_SHORT).show();
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            Toast.makeText(this, "Sai định dạng email", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword() {
        String nhap_mk = matkhau.getText().toString().trim();
        String  nhap_lai_mk = nhaplaimk.getText().toString().trim();
        if (nhap_mk.isEmpty()) {
            Toast.makeText(this, "Mật khẩu đang để trống", Toast.LENGTH_SHORT).show();
            return false;
        }  if (nhap_mk.length()<5) {
            Toast.makeText(this, "Mật khẩu quá ngắn", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!nhap_mk.equals(nhap_lai_mk)) {
            Toast.makeText(this, "Mật khẩu khác nhau", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

}