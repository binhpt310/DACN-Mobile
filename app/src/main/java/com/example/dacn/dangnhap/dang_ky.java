package com.example.dacn.dangnhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacn.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class dang_ky extends AppCompatActivity {

    Button btn_dangky;
    TextView txt_dangnhap;
    public boolean checkmk = true;
    String txt1 = "";
    String txt2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        btn_dangky = findViewById(R.id.btn_dangky);
        txt_dangnhap = findViewById(R.id.dangnhapotrangdangky);
        TextInputLayout nhapmk = findViewById(R.id.txt_nhapmatkhau);
        TextInputLayout nhaplaimk = findViewById(R.id.txt_nhaplaimatkhau);

        nhapmk.getEditText().addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //get the String from CharSequence with s.toString() and process it to validation
                txt1 = s.toString();
            }
        });

        nhaplaimk.getEditText().addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //get the String from CharSequence with s.toString() and process it to validation
                txt2 = s.toString();
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

        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt1 != txt2) {
                    Toast.makeText(dang_ky.this, "Mật khẩu bạn nhập không giống nhau", Toast.LENGTH_SHORT).show();
                    checkmk = false;
                }
                if  (txt1 == txt2){
                    Intent intent = new Intent(dang_ky.this, dang_nhap.class);
                    checkmk =true;
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

}