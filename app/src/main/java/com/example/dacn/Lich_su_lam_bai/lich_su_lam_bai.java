package com.example.dacn.Lich_su_lam_bai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.mucluc.mucluc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class lich_su_lam_bai extends AppCompatActivity {

    LinearLayout llFolderOn, llFolderThi;
    TextView tv_on,tv_thi;
    ConstraintLayout toolbar_lichsulambai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_lam_bai);

        llFolderOn = findViewById(R.id.layout_folder_on);
        llFolderThi = findViewById(R.id.layout_folder_thi);
        tv_on = findViewById(R.id.txt_sodeondalam_lichsulambai);
        tv_thi = findViewById(R.id.txt_sodethidalam_lichsulambai);

        llFolderOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lich_su_lam_bai.this, subjectHistory.class);
                TruyenDuLieu.trDangBai = "review";
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                finish();
            }
        });

        llFolderThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lich_su_lam_bai.this, subjectHistory.class);
                TruyenDuLieu.trDangBai = "exam";
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                finish();
            }
        });

        toolbar_lichsulambai = findViewById(R.id.toolbar_lichsulambai);
        toolbar_lichsulambai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lich_su_lam_bai.this, mucluc.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
            }
        });

        callApi();
    }

    private void callApi() {
        String url = "https://newdacn.onrender.com/count?email="+ TruyenDuLieu.trEmail_dnhap;
        Log.e("url",url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject object = new JSONObject(response);

                    if (object.has("cexam")) {
                        int cexam = object.getInt("cexam");
                        tv_thi.setText(Integer.toString(cexam));
                    }

                    if (object.has("creview")) {
                        int creview = object.getInt("creview");
                        tv_on.setText(Integer.toString(creview));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {}
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                int mStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(stringRequest);

    }

}
