package com.example.dacn.mucluc;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.quenmatkhau.quenmatkhau1;
import com.example.dacn.quenmatkhau.quenmatkhau2;
import com.example.dacn.quenmatkhau.quenmatkhau3;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class thaydoithongtin extends AppCompatActivity {

    CircleImageView ava;
    FloatingActionButton camera;
    ImageView img_back;
    EditText tengndung, matkhau, nhaplaimk;
    Button btn_luuthaydoi;
    String email,tr_tenngdung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thaydoithongtin);

        khaibao();

        tengndung.setText(getIntent().getStringExtra("key_tenngdung"));

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(thaydoithongtin.this, mucluc.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
            }
        });

        btn_luuthaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = TruyenDuLieu.trEmail_dnhap;
                tr_tenngdung = TruyenDuLieu.trTenTk_dnhap;

                HashMap<String, String> map = new HashMap<>();

                map.put("email", email);
                map.put("tenngdung", tengndung.getText().toString());
                map.put("matkhau", matkhau.getText().toString());

                Call<Void> call = retrofitInterface.changeInfo(map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            //backActivity();
                            tr_tenngdung = tengndung.getText().toString().trim();

                            Intent intent = new Intent(thaydoithongtin.this, mucluc.class);
                            startActivity(intent);
                        } else if (response.code() == 400) {
                            Toast.makeText(thaydoithongtin.this,"Lỗi",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(thaydoithongtin.this, t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void khaibao() {
        ava = findViewById(R.id.img_ava);
        camera = findViewById(R.id.btn_camera);
        img_back = findViewById(R.id.img_back_chinhsuathongtin);

        tengndung = findViewById(R.id.txt_tentk_doithongtin);
        matkhau = findViewById(R.id.txt_matkhau_doithongtin);
        nhaplaimk = findViewById(R.id.txt_nhaplaimatkhau_doithongtin);
        btn_luuthaydoi = findViewById(R.id.btn_luuthaydoi);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        Intent intent = new Intent(this, mucluc.class);
        startActivity(intent);
    }

    private boolean validatePassword() {
        String nhap_mk = matkhau.getText().toString().trim();
        String nhap_lai_mk = nhaplaimk.getText().toString().trim();

        if (nhap_lai_mk.isEmpty()) {
            Toast.makeText(this, "Nhập lại mật khẩu đang để trống", Toast.LENGTH_SHORT).show();
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

    /*private void backActivity() {
        String str_tenngdungup = tengndung.getText().toString().trim();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("key_tenngdung", str_tenngdungup);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }*/

}