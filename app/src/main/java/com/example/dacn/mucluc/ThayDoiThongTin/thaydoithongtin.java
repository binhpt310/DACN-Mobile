package com.example.dacn.mucluc.ThayDoiThongTin;

import static android.content.ContentValues.TAG;
import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dacn.R;
import com.example.dacn.RealPathUtil;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.mucluc.mucluc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class thaydoithongtin extends AppCompatActivity {

    public static final int MY_REQUEST_CODE = 10;

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            ava.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    CircleImageView ava;
    FloatingActionButton camera;
    ImageView img_back;
    EditText tengndung, matkhau, nhaplaimk;
    Button btn_luuthaydoi;
    String email,str_tenngdung,str_matkhaumoi,str_nhaplaimk;
    private Uri mUri;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thaydoithongtin);

        khaibao();
        tengndung.setText(TruyenDuLieu.trTenTk_dnhap);
        Glide.with(thaydoithongtin.this).load(TruyenDuLieu.tr_linkanh).into(ava);

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
                //email = TruyenDuLieu.trEmail_dnhap;
                progressDialog = new ProgressDialog(thaydoithongtin.this);
                progressDialog.setMessage("Đợi tí....");

                email = TruyenDuLieu.trEmail_dnhap;
                str_tenngdung = tengndung.getText().toString().trim();
                str_matkhaumoi = matkhau.getText().toString().trim();
                str_nhaplaimk = nhaplaimk.getText().toString().trim();

                if (mUri!=null) {
                    callApiImage();
                } else {
                    callApi();
                }

            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
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

    private void callApiImage() {
        progressDialog.show();

        RequestBody requestBody_Email = RequestBody.create(MediaType.parse("multipart/from-data"),email);
        RequestBody requestBody_Tenngdung = RequestBody.create(MediaType.parse("multipart/from-data"),str_tenngdung);
        RequestBody requestBody_Matkhau = RequestBody.create(MediaType.parse("multipart/from-data"),str_matkhaumoi);

        String strRealPath = RealPathUtil.getRealPath(thaydoithongtin.this,mUri);
        File file = new File(strRealPath);

        RequestBody requestBodyavt = RequestBody.create(MediaType.parse("multipart/from-data"),file);
        MultipartBody.Part multiAvt = MultipartBody.Part.createFormData("image",file.getName(),requestBodyavt);

        retrofitInterface.changeInfo2(requestBody_Email,multiAvt,requestBody_Tenngdung,requestBody_Matkhau).enqueue(new Callback<dulieu_thaydoi>() {
            @Override
            public void onResponse(Call<dulieu_thaydoi> call, Response<dulieu_thaydoi> response) {
                dulieu_thaydoi dulieu_thaydoi1 = response.body();
                if (response.code() == 200 || response.code() == 201 || response.code() == 202 || response.code() == 203) {
                    progressDialog.dismiss();
                    Toast.makeText(thaydoithongtin.this, "Cập nhật thông tin thành công",Toast.LENGTH_LONG).show();
                }
                TruyenDuLieu.trTenTk_dnhap = dulieu_thaydoi1.getTenngdung();
                TruyenDuLieu.tr_linkanh = dulieu_thaydoi1.getAvatar();
            }

            @Override
            public void onFailure(Call<dulieu_thaydoi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(thaydoithongtin.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void callApi() {
        HashMap<String, String> map = new HashMap<>();

        map.put("email", email);
        map.put("tenngdung", str_tenngdung);
        map.put("matkhau", str_matkhaumoi);

        Call<Void> call = retrofitInterface.changeInfo(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    progressDialog.dismiss();
                    Toast.makeText(thaydoithongtin.this, "Cập nhật thông tin thành công",Toast.LENGTH_LONG).show();
                    TruyenDuLieu.trTenTk_dnhap = str_tenngdung;
                } else if (response.code() == 400) {
                    progressDialog.dismiss();
                    Toast.makeText(thaydoithongtin.this, "Tài khoản mail không tồn tại",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(thaydoithongtin.this, "Lỗi kết nối",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission,MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
    }
}