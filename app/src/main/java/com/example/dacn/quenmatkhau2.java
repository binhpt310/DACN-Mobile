package com.example.dacn;

import static android.content.ContentValues.TAG;
import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class quenmatkhau2 extends AppCompatActivity {

    EditText ed_otp1,ed_otp2,ed_otp3,ed_otp4;
    Button btn_xacnhan;
    String otp,otp1,otp2,otp3,otp4, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau2);

        khaibao();

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
                gangtri();
                email = TruyenEmail.trEmail;
                Log.e("mm", email);
                Log.e("dd", otp);

                HashMap<String, String> map = new HashMap<>();

                map.put("email", email);
                map.put("otp", otp);

                Call<Void> call = retrofitInterface.checkOTP(map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Toast.makeText(quenmatkhau2.this,"ok",Toast.LENGTH_SHORT).show();
                        } else if (response.code() == 400) {
                            Toast.makeText(quenmatkhau2.this,"Email không tồn tại",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(quenmatkhau2.this, t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    private void gangtri() {
        otp1 = ed_otp1.getText().toString();
        otp2 = ed_otp2.getText().toString();
        otp3 = ed_otp3.getText().toString();
        otp4 = ed_otp4.getText().toString();
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