package com.example.dacn.mucluc;

import static android.content.ContentValues.TAG;
import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dacn.R;
import com.example.dacn.RealPathUtil;
import com.example.dacn.TruyenDuLieu;
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
    private static int RESULT_LOAD_IMAGE = 1;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    CircleImageView ava;
    FloatingActionButton camera;
    ImageView img_back;
    EditText tengndung, matkhau, nhaplaimk;
    Button btn_luuthaydoi;
    String email;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thaydoithongtin);

        khaibao();
        tengndung.setText(TruyenDuLieu.trTenTk_dnhap);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(thaydoithongtin.this, mucluc.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();*/
                if (mUri!=null) {
                    callApiImage();
                }
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

    private void backActivity() {
        String trEmail_thaydoi = tengndung.getText().toString().trim();
        TruyenDuLieu.trTenTk_dnhap = trEmail_thaydoi;
        finish();
    }

    private void callApiImage() {
        //email = TruyenDuLieu.trEmail_dnhap;
        email = "hoangoanh711@gmail.com";
        Log.e("api email", email);


        //pass it like this
        /*String strRealPath = RealPathUtil.getRealPath(thaydoithongtin.this,mUri);
        File file = new File(strRealPath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        // add another part within the multipart request
        RequestBody fullName =
                RequestBody.create(MediaType.parse("multipart/form-data"), email);

        retrofitInterface.updateProfile(fullName, body);*/

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/from-data"),email);
        String strRealPath = RealPathUtil.getRealPath(thaydoithongtin.this,mUri);
        Log.e("đacn", strRealPath);
        File file = new File(strRealPath);
        RequestBody requestBodyavt = RequestBody.create(MediaType.parse("multipart/from-data"),file);
        MultipartBody.Part multiAvt = MultipartBody.Part.createFormData("image",file.getName(),requestBodyavt);

        retrofitInterface.changeAvatar(requestBody,multiAvt).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    Log.e(TAG,"api img ok");

                } else if (response.code() == 400) {
                    Toast.makeText(thaydoithongtin.this,"Lỗi",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG,"api img lỗi gì đó");
                Toast.makeText(thaydoithongtin.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            mUri = imageUri;
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ava.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onStart(){
        super.onStart();
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mở thư viện
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}