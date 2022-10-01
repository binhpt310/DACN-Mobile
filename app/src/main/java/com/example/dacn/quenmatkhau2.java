package com.example.dacn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class quenmatkhau2 extends AppCompatActivity {

    EditText ed_otp1,ed_otp2,ed_otp3,ed_otp4;
    Button btn_xacnhan;
    String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau2);

        khaibao();

       // gangtri();

        String otp1 = ed_otp1.getText().toString();
        String otp2 = ed_otp2.getText().toString();
        String otp3 = ed_otp3.getText().toString();
        String otp4 = ed_otp4.getText().toString();

        //otp = String.format(otp1,otp2,otp3,otp4);
        otp = otp1 + otp2 + otp3 + otp4;

        ed_otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (ed_otp1.getText().toString().length()==1) {
                    ed_otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ed_otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (ed_otp2.getText().toString().length()==1) {
                    ed_otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ed_otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (ed_otp3.getText().toString().length()==1) {
                    ed_otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("hihi", otp.toString());
            }
        });

    }

    private void gangtri() {
        String otp1 = ed_otp1.getText().toString();
        String otp2 = ed_otp2.getText().toString();
        String otp3 = ed_otp3.getText().toString();
        String otp4 = ed_otp4.getText().toString();

        //otp = String.format(otp1,otp2,otp3,otp4);
        otp = otp1 + otp2 + otp3 + otp4;
    }

    private void khaibao() {
        ed_otp1 = findViewById(R.id.ed_otp1);
        ed_otp2 = findViewById(R.id.ed_otp2);
        ed_otp3 = findViewById(R.id.ed_otp3);
        ed_otp4 = findViewById(R.id.ed_otp4);

        btn_xacnhan = findViewById(R.id.btn_xacnhan);
    }
}