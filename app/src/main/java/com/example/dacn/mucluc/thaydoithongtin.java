package com.example.dacn.mucluc;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
    private static int RESULT_LOAD_IMAGE = 1;
    CircleImageView ava;
    FloatingActionButton camera;
    ImageView img_back;
    EditText tengndung, matkhau, nhaplaimk;
    Button btn_luuthaydoi;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thaydoithongtin);

        khaibao();
        tengndung.setText(TruyenDuLieu.trTenTk_dnhap);

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

                HashMap<String, String> map = new HashMap<>();

                map.put("email", email);
                map.put("tenngdung", tengndung.getText().toString());
                map.put("matkhau", matkhau.getText().toString());

                Call<Void> call = retrofitInterface.changeInfo(map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            backActivity();
                            Toast.makeText(thaydoithongtin.this,"OK",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(thaydoithongtin.this, mucluc.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                            finish();
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

    /*private void backActivity() {
        String str_tenngdungup = tengndung.getText().toString().trim();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("key_tenngdung", str_tenngdungup);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }*/

    private void backActivity() {
        String trEmail_thaydoi = tengndung.getText().toString().trim();
        TruyenDuLieu.trTenTk_dnhap = trEmail_thaydoi;
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK){

            Uri imageUri = data.getData();
            ava.setImageURI(imageUri);
        }

    }

    @Override
    public void onStart(){
        super.onStart();
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

}