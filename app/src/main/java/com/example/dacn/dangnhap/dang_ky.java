package com.example.dacn.dangnhap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacn.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class dang_ky extends AppCompatActivity{

    boolean temp = false;
    String chckppw ="i";
    String passwrd ="i";
    TextInputEditText nhaplaimk;
    TextInputEditText nhapmk;
    TextInputLayout email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        Button btn_dangky = (Button) findViewById(R.id.btn_dangky);
        TextView txt_dangnhap = (TextView) findViewById(R.id.dangnhapotrangdangky);
        nhapmk = findViewById(R.id.edit_txt_mk);
        nhaplaimk = findViewById(R.id.edit_txt_nhaplaimk);
        email = findViewById(R.id.txt_email_ngdung);

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
            public void onClick(View v) {
                if (validatePassword() && validateEmail()) {
                    Intent intent = new Intent(dang_ky.this, dang_nhap.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
//        nhapmk.addTextChangedListener(new TextWatcher() {
//            public void afterTextChanged(Editable s) {
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //get the String from CharSequence with s.toString() and process it to validation
//                passwrd = s.toString();
//            }
//        });
//
//        nhaplaimk.addTextChangedListener(new TextWatcher() {
//            public void afterTextChanged(Editable s) {
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //get the String from CharSequence with s.toString() and process it to validation
//                chckppw = s.toString();
//            }
//        });
    }

    private boolean validateEmail() {
        String emailInput = email.getEditText().getText().toString().trim();
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
        String nhap_mk = nhapmk.getText().toString().trim();
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