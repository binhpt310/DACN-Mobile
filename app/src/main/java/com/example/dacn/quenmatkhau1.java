package com.example.dacn;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dacn.dangnhap.DangNhapResult;
import com.example.dacn.dangnhap.dang_nhap;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class quenmatkhau1 extends AppCompatActivity {

    EditText ed_email;
    Button btn_guiotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau1);

        ed_email = findViewById(R.id.ed_email_quenmk);
        btn_guiotp = findViewById(R.id.btn_guiotp);

        btn_guiotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();

                map.put("email", ed_email.getText().toString());

                Call<CheckEmailResult> call = retrofitInterface.checkemail(map);

                call.enqueue(new Callback<CheckEmailResult>() {
                    @Override
                    public void onResponse(Call<CheckEmailResult> call, Response<CheckEmailResult> response) {
                        if (response.code() == 200) {
                            Toast.makeText(quenmatkhau1.this,"ok",Toast.LENGTH_SHORT).show();
                        } else if (response.code() == 404) {
                            Toast.makeText(quenmatkhau1.this,"Email không tồn tại",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CheckEmailResult> call, Throwable t) {
                        Toast.makeText(quenmatkhau1.this, t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}