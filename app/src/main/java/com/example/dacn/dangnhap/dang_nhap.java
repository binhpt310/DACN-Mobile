package com.example.dacn.dangnhap;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacn.RetrofitInterface;
import com.example.dacn.R;
import com.example.dacn.quenmatkhau1;
import com.example.dacn.trangchu2;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dang_nhap extends AppCompatActivity {

    Button btn_dangnhap;
    TextView txt_dangky, txt_quenmk;
    TextInputEditText email, matkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        khaibao();

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();

                map.put("email", email.getText().toString());
                map.put("matkhau", matkhau.getText().toString());

                Call<DangNhapResult> call = retrofitInterface.executeLogin(map);
                call.enqueue(new Callback<DangNhapResult>() {
                    @Override
                    public void onResponse(Call<DangNhapResult> call, Response<DangNhapResult> response) {
                        if (response.code() == 200) {
                            Intent intent = new Intent(dang_nhap.this, trangchu2.class);
                            startActivity(intent);
                            finish();
                        } else if (response.code() == 401) {
                            Toast.makeText(dang_nhap.this, "Email không tồn tại", Toast.LENGTH_LONG).show();
                        } else if (response.code() == 402) {
                            Toast.makeText(dang_nhap.this, "Sai mật khẩu", Toast.LENGTH_LONG).show();
                        } else if (response.code() == 404) {
                            Toast.makeText(dang_nhap.this, "Lỗi", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DangNhapResult> call, Throwable t) {
                        Toast.makeText(dang_nhap.this, t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        txt_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dang_nhap.this, dang_ky.class);
                startActivity(intent);
                finish();
            }
        });

        txt_quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dang_nhap.this, quenmatkhau1.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void khaibao() {

        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        txt_dangky = findViewById(R.id.btn_dangkyotrangdangnhap);

        email = findViewById(R.id.txtdn_email);
        matkhau = findViewById(R.id.txtdn_matkhau);

        txt_quenmk = findViewById(R.id.txt_quenmk);
    }
}