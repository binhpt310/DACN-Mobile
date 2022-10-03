package com.example.dacn.quenmatkhau;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class quenmatkhau3 extends AppCompatActivity {

    EditText ed_matkhau, ed_nhaplaimk;
    Button btn_capnhat;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau3);

        ed_matkhau = findViewById(R.id.ed_matkhau_quenmk);
        ed_nhaplaimk = findViewById(R.id.ed_nhaplaimatkhau_quenmk);
        btn_capnhat = findViewById(R.id.btn_capnhat);

        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = TruyenDuLieu.trEmail_quenmk;
                if (validatePassword()) {
                    HashMap<String, String> map = new HashMap<>();

                    map.put("email", email);
                    map.put("matkhau", ed_matkhau.getText().toString());

                    Call<Void> call = retrofitInterface.changePassword(map);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200) {
                                Intent intent = new Intent(quenmatkhau3.this, quenmatkhau4.class);
                                startActivity(intent);
                                finish();
                            } else if (response.code() == 400) {
                                Toast.makeText(quenmatkhau3.this, "lỗi",Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }

    private boolean validatePassword() {
        String nhap_mk = ed_matkhau.getText().toString().trim();
        String nhap_lai_mk = ed_nhaplaimk.getText().toString().trim();
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