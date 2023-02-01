package com.example.dacn.quenmatkhau;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.dangnhap.dang_nhap;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class quenmatkhau1 extends AppCompatActivity {

    EditText ed_email;
    Button btn_guiotp;
    TextView taiday;
    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quenmatkhau1);

        ed_email = findViewById(R.id.ed_email_quenmk);
        btn_guiotp = findViewById(R.id.btn_guiotp);
        taiday = findViewById(R.id.btn_taiday_quenmk);

        progressdialog = new ProgressDialog(quenmatkhau1.this);
        progressdialog.setMessage("Loadinggg");

        btn_guiotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressdialog.show();
                HashMap<String, String> map = new HashMap<>();
                map.put("email", ed_email.getText().toString());

                Call<Void> call = retrofitInterface.checkemail(map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            progressdialog.dismiss();
                            nextActivity();
                        } else if (response.code() == 400) {
                            progressdialog.dismiss();
                            Toast.makeText(quenmatkhau1.this,"Email không tồn tại",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(quenmatkhau1.this, t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        taiday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(quenmatkhau1.this, dang_nhap.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                finish();
            }
        });
    }
    private void nextActivity() {
        String truyenEmail = ed_email.getText().toString().trim();
        TruyenDuLieu.trEmail_quenmk = truyenEmail;

        Intent intent = new Intent(quenmatkhau1.this, quenmatkhau2.class);
        startActivity(intent);
    }
}