package com.example.dacn.quenmatkhau;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.example.dacn.TruyenDuLieu;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class quenmatkhau2 extends AppCompatActivity {

    EditText ed_otp1,ed_otp2,ed_otp3,ed_otp4;
    Button btn_xacnhan;
    String otp,otp1,otp2,otp3,otp4, email;
    TextView guilai,diensdt;
    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau2);

        khaibao();

        diensdt.append(TruyenDuLieu.trEmail_quenmk);

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

        progressdialog = new ProgressDialog(quenmatkhau2.this);
        progressdialog.setMessage("Loadinggg");

        btn_xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gangtri();
                email = TruyenDuLieu.trEmail_quenmk;
                HashMap<String, String> map = new HashMap<>();

                map.put("email", email);
                map.put("otp", otp);

                progressdialog.show();
                Call<Void> call = retrofitInterface.checkOTP(map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            progressdialog.dismiss();
                            Intent intent = new Intent(quenmatkhau2.this, quenmatkhau3.class);
                            startActivity(intent);
                            finish();
                        } else if (response.code() == 201) {
                            progressdialog.dismiss();
                            Toast.makeText(quenmatkhau2.this, "Mã OTP đã hết hiệu lực, bạn nhập lại nhé!",Toast.LENGTH_SHORT).show();
                            xoagiatri();
                        } else if (response.code() == 202) {
                            progressdialog.dismiss();
                            Toast.makeText(quenmatkhau2.this, "Sai OTP",Toast.LENGTH_SHORT).show();
                            xoagiatri();
                        } else if (response.code() == 400) {
                            progressdialog.dismiss();
                            Toast.makeText(quenmatkhau2.this, "Không tìm thấy tài khoản email",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(quenmatkhau2.this, t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        guilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = TruyenDuLieu.trEmail_quenmk;
                HashMap<String, String> map = new HashMap<>();

                map.put("email", email);

                Call<Void> call = retrofitInterface.checkemail(map);

                progressdialog.show();
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            progressdialog.dismiss();
                            Toast.makeText(quenmatkhau2.this,"Đã gửi OTP",Toast.LENGTH_SHORT).show();
                        } else if (response.code() == 400) {
                            progressdialog.dismiss();
                            Toast.makeText(quenmatkhau2.this,"Lỗi",Toast.LENGTH_SHORT).show();
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

    private void xoagiatri() {
        ed_otp1.getText().clear();
        ed_otp2.getText().clear();
        ed_otp3.getText().clear();
        ed_otp4.getText().clear();
        ed_otp1.requestFocus();
    }

    private void khaibao() {
        ed_otp1 = findViewById(R.id.ed_otp1);
        ed_otp2 = findViewById(R.id.ed_otp2);
        ed_otp3 = findViewById(R.id.ed_otp3);
        ed_otp4 = findViewById(R.id.ed_otp4);
        guilai = findViewById(R.id.btn_guilai_quenmk);
        btn_xacnhan = findViewById(R.id.btn_xacnhan);
        diensdt = findViewById(R.id.txt_qmk2);
    }
}